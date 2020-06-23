package com.cloud.apigenerator.service.impl;



import com.cloud.apigenerator.config.AutoGenConfig;
import com.cloud.apigenerator.entity.ColumnEntity;
import com.cloud.apigenerator.entity.TableEntity;
import com.cloud.apigenerator.service.GenerateCode;
import com.cloud.apigenerator.service.GenerateCodePathConfig;
import com.cloud.apigenerator.util.DateUtils;
import com.cloud.apigenerator.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityCode extends GenerateCode {

    public TableEntity autoGenEntity;


    public EntityCode(TableEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }

    @Override
    public void generateAnInstance() {

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", autoGenEntity.getTableName());
        map.put("comments", autoGenEntity.getComments());
        map.put("className", autoGenEntity.getClassName());
        map.put("columns", autoGenEntity.getColumns());
        map.put("version", AutoGenConfig.version);
        map.put("package", AutoGenConfig.entityPackageOutPath);
        map.put("moduleName", AutoGenConfig.entityBasePath);
        map.put("author", AutoGenConfig.author);
        map.put("email", "c1111");
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        map.put("date", DateUtils.format(new Date(), DateUtils.DATE_PATTERN));

        for(ColumnEntity columnEntity: autoGenEntity.getColumns()){
            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAddrNames(StringUtils.uncapitalize(attrName));
            //列的数据类型，转换成Java类型
            String attrType = sqlType2JavaType(columnEntity.getDataType());
            columnEntity.setAttrType(attrType);
        }
        VelocityContext context = new VelocityContext(map);

        List<String> templates = GenerateCodePathConfig.getTemplates();
        for(String template : templates){
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                String entityPath = GenerateCodePathConfig.getEntityPath() + AutoGenConfig.entityBasePath + File.separator
                        + Utils.initcap(autoGenEntity.getClassName()) + ".java";
                Utils.writeFile(entityPath, sw.toString());
            } catch (Exception e) {

            }
        }
    }


    /**
     * 功能：生成实体类主体代码 （Entity or model）
     *
     * @return
     */



    private String sqlType2JavaType(String sqlType) {
        if (sqlType.indexOf("(") > -1) {
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
