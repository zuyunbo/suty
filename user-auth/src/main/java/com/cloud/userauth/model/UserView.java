package com.cloud.userauth.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zuyunbo
 */
@Data
@Builder
@ApiModel(value="登录对象", description="登录对象")
public class UserView {

    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;


}
