package com.cloud.apigenerator.config;

import java.io.File;

public interface AutoGenConfig {


    /**
     * 表名
     */
    String tableName = "user_info";
    /**
     * 公共配置
     */
    String srcBasePath = "java";

    String projectPath = File.separator + "src" + File.separator + "main";

    String version = "1.0";

    String author = "2u";

    /**
     * entity config
     */
    String entityPackageOutPath = "com.cloud.apimodel";

    String entityBasePath = "entity";

    /**
     * xml config
     */
    String xmlPackageOutPath = "com.cloud.apiservice";

     String daoBasePath = "mapper";



}
