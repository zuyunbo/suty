package com.cloud.apigenerator.service.impl;

import com.cloud.apigenerator.config.AutoGenConfig;
import com.cloud.apigenerator.entity.TableEntity;
import com.cloud.apigenerator.service.GenerateCode;
import com.cloud.apigenerator.service.GenerateCodePathConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author 2u
 */
@Slf4j
public class DaoCode extends GenerateCode {

    public TableEntity autoGenEntity;


    public DaoCode(TableEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }

    @Override
    public void generateAnInstance() {
        Map<String, Object> codeMap = GenerateCodePathConfig.getCodeMap(autoGenEntity, AutoGenConfig.entityPackageOutPath, AutoGenConfig.entityBasePath);
        GenerateCodePathConfig.writeCode(codeMap,AutoGenConfig.daoWritePath,GenerateCodePathConfig.getDaoPath(),AutoGenConfig.daoBasePath,autoGenEntity.getClassName()+"Mapper");
    }


}
