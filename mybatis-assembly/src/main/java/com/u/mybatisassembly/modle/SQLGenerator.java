package com.u.mybatisassembly.modle;

import com.sun.deploy.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Set;

/**
 * @author zuyunbo
 */
public class SQLGenerator<T> {

    protected Logger logger    = LoggerFactory.getLogger(this.getClass());
    private Set<String> columns;
    private String        tableName;
    private String        columnsStr;
    private String        pkName;
    private String        seq;


    public SQLGenerator(Set<String> columns, String tableName, String pkName,
                        String seq) {
        super();
        this.columns = columns;
        this.tableName = tableName;
        this.pkName = pkName;
        this.seq = seq;
        this.columnsStr = StringUtils.join(this.columns, ",");
    }

    /**
     * 生成新增的SQL
     *
     * @param t
     * @param currentColumnFieldNames
     * @return
     */
    public String sql_create(T t, Map<String, String> currentColumnFieldNames) {

        StringBuilder sql_build = new StringBuilder();
        sql_build.append("INSERT INTO ").append(tableName).append("(")
                .append(columnsStr).append(")values(");
        String sql = sql_build.toString();

        logger.debug("生成的SQL为: " + sql);

        return sql;
    }





}
