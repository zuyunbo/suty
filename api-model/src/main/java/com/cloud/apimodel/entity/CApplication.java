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
 * @since 1.0 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Api(description = "c_application")
public class CApplication extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 类型 
#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}
     */
     @ApiModelProperty("类型 
#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}")
     private String appType;
    /**
     * 客户端ID
     */
     @ApiModelProperty("客户端ID")
     private String clientId;
    /**
     * 客户端密码
     */
     @ApiModelProperty("客户端密码")
     private String clientSecret;
    /**
     * 创建时间
     */
     @ApiModelProperty("创建时间")
     private String createTime;
    /**
     * 创建人id
     */
     @ApiModelProperty("创建人id")
     private Long createdBy;
    /**
     * 备注
     */
     @ApiModelProperty("备注")
     private String describe;
    /**
     * 应用图标
     */
     @ApiModelProperty("应用图标")
     private String icon;
    /**
     * 应用名称
     */
     @ApiModelProperty("应用名称")
     private String name;
    /**
     * 状态
     */
     @ApiModelProperty("状态")
     private Boolean state;
    /**
     * 更新时间
     */
     @ApiModelProperty("更新时间")
     private String updateTime;
    /**
     * 更新人id
     */
     @ApiModelProperty("更新人id")
     private Long updatedBy;
    /**
     * 官网
     */
     @ApiModelProperty("官网")
     private String website;
}
