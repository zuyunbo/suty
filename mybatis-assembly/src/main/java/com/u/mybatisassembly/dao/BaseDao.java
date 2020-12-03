package com.u.mybatisassembly.dao;

import com.u.mybatisassembly.modle.*;
import com.u.mybatisassembly.service.BaseService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @param <T>
 * @param <PK>
 * @author 2u
 */
public abstract class BaseDao<T extends BaseModle<T>, PK extends Serializable> implements BaseService<T, PK> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "sqlSessionTemplateASS")
    public SqlSessionTemplate sqlSessionTemplateASS;
    private Class<T> entityClass;
    /**
     * 实体类主键名称
     */
    private String pkName;
    /**
     * 实体类ID字段名称
     */
    private String idName;
    //主键的序列
    private String seq;
    private String tableName;
    /**
     * 作cache 结构{T类的镜像,{数据库列名,实体字段名}}
     */
    private static final Map<Class<?>, Map<String, String>> classFieldMap = new HashMap<Class<?>, Map<String, String>>();
    private Map<String, String> currentColumnFieldNames;

    private SQLGenerator<T> sqlGenerator;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        this.entityClass = (Class<T>) this.getClass().getGenericSuperclass();
        currentColumnFieldNames = classFieldMap.computeIfAbsent(entityClass, k -> new LinkedHashMap<String, String>());
        Field[] fields = this.entityClass.getDeclaredFields();
        String fieldName = null;
        String columnName = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Ignore.class)) {
                continue;
            }
            fieldName = field.getName();
            TableColumn tableColumn = field.getAnnotation(TableColumn.class);
            if (null != tableColumn) {
                columnName = tableColumn.value();
            } else {
                columnName = null;
            }
            columnName = (StringUtils.isEmpty(columnName) ? fieldName : columnName);
            currentColumnFieldNames.put(columnName, fieldName);
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                idName = fieldName;
                pkName = columnName;
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                seq = primaryKey.seq();
            }
        }

        Table table = this.entityClass.getAnnotation(Table.class);
        if (null == table) { throw new RuntimeException("类-"
                + this.entityClass + ",未用@Table注解标识!!"); }
        tableName = table.value();
  /*      sqlGenerator = new SQLGenerator<T>(currentColumnFieldNames.keySet(),
                tableName,pkName,seq);*/
    }

    @Override
    public void create(T t) {
        sqlSessionTemplateASS.insert("create",
                sqlGenerator.sql_create(t, currentColumnFieldNames));
    }


}
