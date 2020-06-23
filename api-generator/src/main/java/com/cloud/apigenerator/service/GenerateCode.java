package com.cloud.apigenerator.service;

import com.cloud.apigenerator.config.AutoGenConfig;

import java.io.File;

/**
 * @author   ynb2u
 */
public abstract class GenerateCode {

     public GenerateCode generateCode;


     public void setNextGenerateCode(GenerateCode generateCode){
          generateCode = generateCode;
     }

     public void isToExecute(){
            generateAnInstance();
           if(generateCode!=null){
               generateCode.isToExecute();
           }
     }

    public abstract  void generateAnInstance();

}
