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
 * @since 1.0 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Api(description = "sys_user")
public class SysUser extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 同步工作流引擎(1-同步,0-不同步)
     */
     @ApiModelProperty("同步工作流引擎(1-同步,0-不同步)")
     private Byte activitiSync;
    /**
     * 头像
     */
     @ApiModelProperty("头像")
     private String avatar;
    /**
     * 生日
     */
     @ApiModelProperty("生日")
     private String birthday;
    /**
     * 设备ID
     */
     @ApiModelProperty("设备ID")
     private String clientId;
    /**
     * 创建人
     */
     @ApiModelProperty("创建人")
     private String createBy;
    /**
     * 创建时间
     */
     @ApiModelProperty("创建时间")
     private String createTime;
    /**
     * 删除状态(0-正常,1-已删除)
     */
     @ApiModelProperty("删除状态(0-正常,1-已删除)")
     private Byte delFlag;

    /**
     * 电子邮件
     */
     @ApiModelProperty("电子邮件")
     private String email;
    /**
     * 机构编码
     */
     @ApiModelProperty("机构编码")
     private String orgCode;
    /**
     * 密码
     */
     @ApiModelProperty("密码")
     private String password;
    /**
     * 电话
     */
     @ApiModelProperty("电话")
     private String phone;
    /**
     * 职务，关联职务表
     */
     @ApiModelProperty("职务，关联职务表")
     private String post;
    /**
     * 真实姓名
     */
     @ApiModelProperty("真实姓名")
     private String realname;
    /**
     * 多租户标识
     */
     @ApiModelProperty("多租户标识")
     private String relTenantIds;
    /**
     * md5密码盐
     */
     @ApiModelProperty("md5密码盐")
     private String salt;
    /**
     * 性别(0-默认未知,1-男,2-女)
     */
     @ApiModelProperty("性别(0-默认未知,1-男,2-女)")
     private Byte sex;
    /**
     * 性别(1-正常,2-冻结)
     */
     @ApiModelProperty("性别(1-正常,2-冻结)")
     private Byte status;
    /**
     * 座机号
     */
     @ApiModelProperty("座机号")
     private String telephone;
    /**
     * 第三方登录的唯一标识
     */
     @ApiModelProperty("第三方登录的唯一标识")
     private String thirdId;
    /**
     * 第三方类型
     */
     @ApiModelProperty("第三方类型")
     private String thirdType;
    /**
     * 更新人
     */
     @ApiModelProperty("更新人")
     private String updateBy;
    /**
     * 更新时间
     */
     @ApiModelProperty("更新时间")
     private String updateTime;
    /**
     * 身份（1普通成员 2上级）
     */
     @ApiModelProperty("身份（1普通成员 2上级）")
     private Byte userIdentity;
    /**
     * 登录账号
     */
     @ApiModelProperty("登录账号")
     private String username;
    /**
     * 工号，唯一键
     */
     @ApiModelProperty("工号，唯一键")
     private String workNo;
}
