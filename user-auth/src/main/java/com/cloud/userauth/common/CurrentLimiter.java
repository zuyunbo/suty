package com.cloud.userauth.common;

import java.util.Vector;

public class CurrentLimiter {

    /** 本地线程变量，存储一次请求获取到的通行证，和其他并发请求隔离开，在controller执行完后释放本次请求获得的通行证 */
    private static ThreadLocal<Vector<LimiteRule>> localAcquiredLimiteRules = new ThreadLocal<Vector<LimiteRule>>();

    /** 所有限流规则 */
    private static Vector<LimiteRule> allLimiteRules = new Vector<LimiteRule>();

    /** 私有构造器，避免实例化 */
    private CurrentLimiter() {}

    /**
     * 添加限流规则，在spring启动时添加，不需要加锁，如果在运行中动态添加，需要加锁
     * @param rule
     */
    public static void addRule(LimiteRule rule) {
        printRule(rule);
        allLimiteRules.add(rule);
    }

    /**
     * 获取流量通信证，所有流量规则都要获取后才能通过，如果一个不能获取则抛出异常
     * 多线程并发，需要加锁
     * @param urlPath
     */
    public static void tryAcquire(String urlPath) throws Exception {
        //有限流规则则处理
        if (allLimiteRules.size() > 0) {

            //能获取到通行证的流量规则要保存下来，在Controller执行完后要释放
            Vector<LimiteRule> acquiredLimitRules = new Vector<LimiteRule>();

            for(LimiteRule rule:allLimiteRules) {
                //获取通行证
                AcquireResult acquireResult = rule.tryAcquire(urlPath);

                if (acquireResult.getResult() == AcquireResult.ACQUIRE_SUCCESS) {
                    acquiredLimitRules.add(rule);
                    //获取到通行证的流量规则添加到本地线程变量
                    localAcquiredLimiteRules.set(acquiredLimitRules);

                } else if (acquireResult.getResult() == AcquireResult.ACQUIRE_FAILED) {
                    //如果获取不到通行证则抛出异常
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("The request [").append(urlPath).append("] exceeds maximum traffic limit, the limit is ").append(rule.getMaxConcurrent())
                            .append(", available permit is").append(acquireResult.getAvailablePermits()).append(".");

                    System.out.println(buffer);
                    throw new Exception(buffer.toString());

                } else {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("This path does not match the limit rule, path is [").append(urlPath)
                            .append("], pattern is [").append(rule.getPattern()).append("].");
                    System.out.println(buffer.toString());
                }
            }
        }
    }

    /**
     * 释放获取到的通行证。在controller执行完后掉调用（抛出异常也需要调用）
     */
    public static void release() {
        Vector<LimiteRule> acquiredLimitRules = localAcquiredLimiteRules.get();
        if (null != acquiredLimitRules && acquiredLimitRules.size() > 0) {
            acquiredLimitRules.forEach(rule->{
                rule.release();
            });
        }

        //destory本地线程变量，避免内存泄漏
        localAcquiredLimiteRules.remove();
    }

    /**
     * 打印限流规则信息
     * @param rule
     */
    private static void printRule(LimiteRule rule) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Add Limit Rule, Max Concurrent: ").append(rule.getMaxConcurrent())
                .append(", Pattern: ").append(rule.getPattern());
        System.out.println(buffer.toString());
    }
}