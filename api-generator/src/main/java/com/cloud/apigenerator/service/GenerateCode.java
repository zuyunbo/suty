package com.cloud.apigenerator.service;


/**
 * @author   ynb2u
 */
public abstract class GenerateCode {

     public GenerateCode generateCode;

     public void setNextGenerateCode(GenerateCode generateCode1){
         generateCode = generateCode1;
     }

     public void isToExecute(){
            generateAnInstance();
           if(generateCode!=null){
               generateCode.isToExecute();
           }
     }

    public abstract  void generateAnInstance();

}
