package com.cloud.apigenerator.service.impl;

import com.cloud.apigenerator.config.AutoGenConfig;
import com.cloud.apigenerator.entity.TableEntity;
import com.cloud.apigenerator.service.GenerateCode;
import com.cloud.apigenerator.service.GenerateCodePathConfig;

import java.util.Map;

public class ControllerCode  extends GenerateCode {

    public TableEntity autoGenEntity;


    public ControllerCode(TableEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }

    @Override
    public void generateAnInstance() {
        Map<String, Object> codeMap = GenerateCodePathConfig.getCodeMap(autoGenEntity, AutoGenConfig.controllerPackageOutPath, AutoGenConfig.entityBasePath);
        GenerateCodePathConfig.writeCode(codeMap,AutoGenConfig.controllerWritePath,GenerateCodePathConfig.getControllerPath(),"controller",autoGenEntity.getClassName()+"Controller");
    }

}
