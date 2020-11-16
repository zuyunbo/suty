package com.cloud.apigenerator.config;

import java.io.File;

/**
 * @author 2u
 */
public interface AutoGenConfig {


    /**
     * 表名
     */
    String tableName = "sys_user";
    /**
     * 公共配置
     */
    String srcBasePath = "java";

    String resBasePath = "resources";


    String projectPath = File.separator + "src" + File.separator + "main";

    String version = "1.0";

    String author = "2u";

    /**
     * entity config
     */
    String entityPackageOutPath = "com.cloud.apimodel";

    String entityBasePath = "entity";

    String paramBasePath = "param";

    String ParamWritePath="/ztemplate/Param.java.vm";

    String entityWritePath="/ztemplate/Entity.java.vm";


    /**
     * dao config
     */
    String daoPackageOutPath = "com.cloud.apiservice";

    String daoBasePath = "mapper";

    String daoWritePath="/ztemplate/Dao.java.vm";

    String daoXmlWritePath="/ztemplate/Dao.xml.vm";



    /**
     * service config
     */
    String servicePackageOutPath = "com.cloud.apiservice";

    String serviceBasePath = "service";

    String serviceWritePath="/ztemplate/Service.java.vm";

    String serviceImplWritePath="/ztemplate/ServiceImpl.java.vm";


    /**
     * xml config
     */
    String xmlPackageOutPath = "com.cloud.apiservice";

    /**
     * controller config
     */
    String controllerPackageOutPath = "com.cloud.managebackend";

    String controllerWritePath = "/ztemplate/Controller.java.vm";


}
