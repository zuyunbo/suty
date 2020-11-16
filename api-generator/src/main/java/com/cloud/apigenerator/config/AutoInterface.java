package com.cloud.apigenerator.config;

import com.cloud.apigenerator.service.GenerateCode;

/**
 * @ ynb2u
 */
public interface AutoInterface {
    /**
     *  连接池
     */
    void connectionJDBC(String table);


    /**
     * 生成实例组合
     */
    GenerateCode getGenerateCodes();

}
