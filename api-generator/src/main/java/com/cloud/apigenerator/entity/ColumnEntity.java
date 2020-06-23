/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.cloud.apigenerator.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 列的属性
 * 
 * @author 2u
 */
@Data
@Builder
public class ColumnEntity {
	//列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;
    
    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String addrNames;
    //属性类型
    private String attrType;
    //auto_increment
    private String extra;
}
