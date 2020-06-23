package com.cloud.apigenerator.service.impl;


import com.cloud.apigenerator.config.AutoGenConfig;
import com.cloud.apigenerator.entity.TableEntity;
import com.cloud.apigenerator.service.GenerateCode;
import com.cloud.apigenerator.service.GenerateCodePathConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.WordUtils;
import java.util.Map;

/**
 * @author 2u
 */
@Slf4j
public class EntityCode extends GenerateCode {

    public TableEntity autoGenEntity;


    public EntityCode(TableEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }

    /**
     * 生成代码
     */
    @Override
    public void generateAnInstance() {
        Map<String, Object> codeMap = GenerateCodePathConfig.getCodeMap(autoGenEntity, AutoGenConfig.entityPackageOutPath, AutoGenConfig.entityBasePath);
        GenerateCodePathConfig.writeCode(codeMap,AutoGenConfig.entityWritePath,GenerateCodePathConfig.getEntityPath(),AutoGenConfig.entityBasePath,autoGenEntity.getClassName());
    }


    /**
     * 功能：生成实体类主体代码 （Entity or model）
     *
     * @return
     */


    public static String sqlType2JavaType(String sqlType) {
        if (sqlType.contains("(")) {
            sqlType = sqlType.substring(0, sqlType.indexOf("("));
        }
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.startsWith("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }
}
