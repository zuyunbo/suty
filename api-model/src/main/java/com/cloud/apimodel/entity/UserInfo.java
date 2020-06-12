package com.cloud.apimodel.entity;


import com.example.commoncenter.base.BaseEntity;

/**
 * 用户实体
 */
public class UserInfo extends BaseEntity {

    private String userName;

    private Integer passWord;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWord(Integer passWord) {
        this.passWord = passWord;
    }

    public Integer getPassWord() {
        return passWord;
    }

}


