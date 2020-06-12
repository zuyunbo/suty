package com.cloud.apimodel.param;


import com.example.commoncenter.base.BaseQueryParam;

public class UserInfoQueryParam extends BaseQueryParam {
    //按照用户名查询
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

