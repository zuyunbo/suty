package com.cloud.apigenerator.config.impl;

import com.cloud.apigenerator.config.AutoGenConfig;
import com.cloud.apigenerator.config.AutoInterface;
import com.cloud.apigenerator.entity.ColumnEntity;
import com.cloud.apigenerator.entity.TableEntity;
import com.cloud.apigenerator.service.GenerateCode;
import com.cloud.apigenerator.service.impl.*;
import com.cloud.apigenerator.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ynb2u
 * @date 2020/06/15
 */
@Slf4j
public abstract class AutoInterfaceImpl implements AutoInterface {

    private static final String URL = "jdbc:mysql://47.105.35.98:3306/cloud_code";
    private static final String NAME = "root";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection con;

    private TableEntity tableEntity;

    private static final String SQL = "select  column_name, column_comment, data_type from information_schema.columns where table_schema ='cloud_code'  and table_name = '%s'";

    @Override
    public GenerateCode getGenerateCodes() {
        GenerateCode entityCode = new EntityCode(tableEntity);
        ServiceCode serviceCode = new ServiceCode(tableEntity);
        DaoCode daoCode = new DaoCode(tableEntity);
        ParamCode paramCode = new ParamCode(tableEntity);
        ServiceImplCode serviceImplCode = new ServiceImplCode(tableEntity);
        XmlCode xmlCode = new XmlCode(tableEntity);
        ControllerCode controllerCode = new ControllerCode(tableEntity);
        daoCode.setNextGenerateCode(serviceCode);
        serviceCode.setNextGenerateCode(paramCode);
        paramCode.setNextGenerateCode(serviceImplCode);
        serviceImplCode.setNextGenerateCode(xmlCode);
        xmlCode.setNextGenerateCode(controllerCode);
        entityCode.setNextGenerateCode(daoCode);
        return entityCode;

    }

    /**
     * 连接数据库
     */
    @Override
    public void connectionJDBC(String table) {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, NAME, PASS);
            if (con == null) {
                log.error("{} =========> 创建数据库失败", AutoInterfaceImpl.class.getName());
            }
            this.executeQuery(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行结果查询返回
     *
     * @return
     */
    public void executeQuery(String table) {
        List<ColumnEntity> entityList = new ArrayList<>();
        try {
            String sql = String.format(SQL, table);
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.last();
            resultSet.beforeFirst();

            while (resultSet.next()) {
                if ("id".equals(resultSet.getObject("column_name").toString())) {
                    continue;
                }
                Object commentObj = resultSet.getObject("column_comment");
                entityList.add(ColumnEntity.builder().columnName(resultSet.getObject("column_name").toString())
                        .comments(commentObj != null ? commentObj.toString() : "")
                        .dataType(resultSet.getObject("data_type").toString()).build());
            }
        } catch (SQLException e) {
            log.error("=============> {} ", e.getMessage());
        }
        tableEntity = TableEntity.builder().className(Utils.initcap(Utils.replaceUnderlineAndFirstToUpper(table, "_", "")))
                .tableName(table).columns(entityList).build();
    }


}
