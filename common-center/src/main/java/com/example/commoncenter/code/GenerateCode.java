package com.example.commoncenter.code;

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

     public String getBasePath(){
         File directory = new File("");
         return directory.getAbsolutePath() + File.separator + "api-model" + AutoGenConfig.projectPath + File.separator +
                 AutoGenConfig.srcBasePath + File.separator
                 + AutoGenConfig.entityPackageOutPath.replace(".", File.separator) + File.separator;
     }

    public abstract  void generateAnInstance();

}
