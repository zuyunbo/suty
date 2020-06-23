package com.cloud.apigenerator.service;

import com.cloud.apigenerator.config.AutoGenConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerateCodePathConfig {

    public static String getEntityPath(){
        File directory = new File("");
        return directory.getAbsolutePath() + File.separator + "api-model" + AutoGenConfig.projectPath + File.separator +
                AutoGenConfig.srcBasePath + File.separator
                + AutoGenConfig.entityPackageOutPath.replace(".", File.separator) + File.separator;
    }

    public static List<String> getTemplates(){
        List<String> templates = new ArrayList<String>();

        templates.add("/ztemplate/Entity.java.vm");

        return templates;
    }

}
