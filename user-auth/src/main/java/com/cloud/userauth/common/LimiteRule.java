package com.cloud.userauth.common;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.Global.print;

public class LimiteRule {

    /** 信号量 */
    private final Semaphore sema;

    /** 请求URL匹配规则 */
    private final String pattern;

    /** 最大并发数 */
    private final int maxConcurrent;



    public LimiteRule(String pattern, int maxConcurrent) {
        this.sema = new Semaphore(maxConcurrent);
        this.pattern = pattern;
        this.maxConcurrent = maxConcurrent;
    }


    /**
     * 获取通行证。这里加同步是为了打印可用通行证数量时看起来逐个减少或者逐个增加，无此打印需求可不加synchronized关键字
     * @param urlPath 请求Url
     * @return 0-获取成功，1-没有获取到通行证，2-不需要获取通行证
     */
    public synchronized AcquireResult tryAcquire(String urlPath) {

        AcquireResult acquireResult = new AcquireResult();
        acquireResult.setAvailablePermits(this.sema.availablePermits());

        try {
            //Url请求匹配规则则获取通行证
            if (Pattern.matches(pattern, urlPath)) {

                boolean acquire = this.sema.tryAcquire(50, TimeUnit.MILLISECONDS);

                if (acquire) {
                    acquireResult.setResult(AcquireResult.ACQUIRE_SUCCESS);
                } else {
                    acquireResult.setResult(AcquireResult.ACQUIRE_FAILED);
                }
            } else {
                acquireResult.setResult(AcquireResult.ACQUIRE_NONEED);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return acquireResult;
    }

    /**
     * 释放通行证，这里加同步是为了打印可用通行证数量时看起来逐个减少或者逐个增加，无此打印需求可不加synchronized关键字
     */
    public synchronized void release() {
        this.sema.release();
        print(null);
    }

    /**
     * 得到最大并发数
     * @return
     */
    public int getMaxConcurrent() {
        return this.maxConcurrent;
    }



    /**
     * 得到匹配表达式
     * @return
     */
    public String getPattern() {
        return this.pattern;
    }

    /**
     * 打印日志
     * @param urlPath
     */
    private void print(String urlPath) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Pattern: ").append(pattern).append(", ");
        if (null != urlPath) {
            buffer.append("urlPath: ").append(urlPath).append(", ");
        }
        buffer.append("Available Permits:").append(this.sema.availablePermits());
        System.out.println(buffer.toString());
    }



}
