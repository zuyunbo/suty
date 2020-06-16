package com.example.commoncenter.code.specific;

import com.example.commoncenter.code.AutoGenConfig;
import com.example.commoncenter.code.GenerateCode;
import com.example.commoncenter.util.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EntityCode extends GenerateCode {

    public AutoGenEntity autoGenEntity;


    public EntityCode(AutoGenEntity autoGenEntity) {
        this.autoGenEntity = autoGenEntity;
    }

    @Override
    public void generateAnInstance() {

/*        Map<String, Object> map = new HashMap<>();
        map.put("package", AutoGenConfig.entityPackageOutPath);
        map.put("tableName", AutoGenConfig.tableName);
        map.put("classname", Utils.initcap(AutoGenConfig.tableName));*/

        String entityContent = parseEntity();
        String tmpTableName = Utils.replaceUnderlineAndFirstToUpper(AutoGenConfig.tableName.toLowerCase(), "_", "");
        String entityPath = getBasePath() + AutoGenConfig.entityBasePath + File.separator
                + Utils.initcap(tmpTableName) + ".java";
        Utils.writeFile(entityPath, entityContent);
    }


    /**
     * 功能：生成实体类主体代码 （Entity or model）
     *
     * @return
     */
    private String parseEntity() {
        String tmpTableName = Utils.replaceUnderlineAndFirstToUpper(AutoGenConfig.tableName.toLowerCase(), "_", "");
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(AutoGenConfig.entityPackageOutPath).append(".").append(AutoGenConfig.entityBasePath).append(";\r\n");
        sb.append("import com.example.commoncenter.base.BaseEntity;\r\n" +
                "import javax.persistence.*;\r\n" +
                "import io.swagger.annotations.ApiModelProperty;\r\n" +
                "import lombok.Builder;\r\n" +
                "import lombok.Data;\r\n" +
        "import javax.persistence.Transient;\r\n");
        sb.append("\r\n");
        sb.append("@Builder\n");
        sb.append("@Entity\n");
        sb.append("@Data\n");
        sb.append("public class ").append(Utils.initcap(tmpTableName)).append(" extends BaseEntity{\r\n\n");
        //属性
        processAllAttrs(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param content
     */
    private void processAllAttrs(StringBuffer content) {
        for (int i = 0; i < autoGenEntity.getColNames().size(); i++) {
            //将数据库列转小写，去掉下划线并首字母大写
            String colName = (Utils.replaceUnderlineAndFirstToUpper(autoGenEntity.getColNames().get(i).toLowerCase(), "_", ""));
            content.append("\t@ApiModelProperty(\"").append(autoGenEntity.getColComment().get(i)).append("\"")
                    .append(")").append("\r\n\t@Column(name =\"")
                    .append(autoGenEntity.getColNames().get(i)).append("\")")
                    .append("\r\n\tprivate ")
                    .append(sqlType2JavaType(autoGenEntity.getColTypes().get(i)))
                    .append(" ")
                    .append(colName).append(";\r\n\n");
        }
    }


    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
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
}
