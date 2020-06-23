package com.cloud.apimodel.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import com.example.commoncenter.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import lombok.EqualsAndHashCode;


/**
 *
 * @author 2u c1111
 * @since 1.0 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Api(description = "user_info")
public class UserInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 测试用户名

     */
     @ApiModelProperty("测试用户名
")
     private String userName;
    /**
     * 测试用户密码
     */
     @ApiModelProperty("测试用户密码")
     private Integer passWord;
}
