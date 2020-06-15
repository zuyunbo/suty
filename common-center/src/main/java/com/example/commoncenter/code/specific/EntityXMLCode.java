package com.example.commoncenter.code.specific;

import com.example.commoncenter.code.AutoGenConfig;
import com.example.commoncenter.code.GenerateCode;
import com.example.commoncenter.util.Utils;

import java.io.File;

public class EntityXMLCode extends GenerateCode {


    public AutoGenEntity autoGenEntity;

    public EntityXMLCode(AutoGenEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }



    /**
     * 生成xml
     */
    public String parseXml() {

        String tmpTableName = Utils.replaceUnderlineAndFirstToUpper(AutoGenConfig.tableName.toLowerCase(), "_", "");

        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");

        sb.append("<mapper  namespace=\"" + AutoGenConfig.xmlPackageOutPath + "." + AutoGenConfig.daoBasePath + "." + Utils.initcap(tmpTableName) + "Mapper\">\r\n");

        sb.append("<cache readOnly=\"true\" eviction=\"FIFO\"/>\n\n");

        sb.append(" <select id=\"listQuery\" parameterType=\"" + Utils.initcap(tmpTableName) + "QueryParam\" resultType=\"" + Utils.initcap(tmpTableName) + "\">\n" +
                "        SELECT * FROM " + AutoGenConfig.tableName + "\n" +
                "        <if test=\"sortNames!=null and sortNames!='' and sortType!=null and sortType!=''\">\n" +
                "            ORDER BY ${sortNames} ${sortType}\n" +
                "        </if>\n" +
                "    </select>\n\n");

        sb.append("</mapper>");
        return sb.toString();
    }

    @Override
    public void generateAnInstance() {

    }
}
