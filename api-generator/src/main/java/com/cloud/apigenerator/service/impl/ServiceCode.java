package com.cloud.apigenerator.service.impl;

import com.cloud.apigenerator.config.AutoGenConfig;
import com.cloud.apigenerator.entity.TableEntity;
import com.cloud.apigenerator.service.GenerateCode;
import com.cloud.apigenerator.service.GenerateCodePathConfig;

import java.util.Map;

public class ServiceCode extends GenerateCode {


    public TableEntity autoGenEntity;


    public ServiceCode(TableEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }

    /**
     * 生成代码
     */
    @Override
    public void generateAnInstance() {
        Map<String, Object> codeMap = GenerateCodePathConfig.getCodeMap(autoGenEntity, AutoGenConfig.entityPackageOutPath, AutoGenConfig.entityBasePath);
        GenerateCodePathConfig.writeCode(codeMap,AutoGenConfig.serviceWritePath,GenerateCodePathConfig.getDaoPath(),AutoGenConfig.serviceBasePath,autoGenEntity.getClassName()+"Service");
    }

}
