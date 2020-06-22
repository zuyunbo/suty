package com.cloud.apimodel.entity;


import com.cloud.globalexception.config.ExceptionCode;
import com.example.commoncenter.base.BaseEntity;
import io.swagger.annotations.Api;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 角色实体
 */
@Api(description = "角色信息")
public class RoleInfo extends BaseEntity {

    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    @ExceptionCode(value = 100001, message = "账号验证错误")
    private String name;

    private String description;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

