package com.cloud.apimodel.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import com.example.commoncenter.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Mapper;


/**
 *
 * @author admin c1111
 * @since 1.0 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Api(description = "account")
public class Account extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
     @ApiModelProperty("编号")
     private String uId;
    /**
     * 电话
     */
     @ApiModelProperty("电话")
     private String phone;
    /**
     * 姓名
     */
     @ApiModelProperty("姓名")
     private String name;
    /**
     * 身份证
     */
     @ApiModelProperty("身份证")
     private String card;
    /**
     * 状态 0 未启用 1启用
     */
     @ApiModelProperty("状态 0 未启用 1启用")
     private Integer state;
    /**
     * 当前状态1授权激活2资料未完善3待审核4已完成5已取消6审核不通过
     */
     @ApiModelProperty("当前状态1授权激活2资料未完善3待审核4已完成5已取消6审核不通过")
     private Integer status;
    /**
     * 创建时间
     */
     @ApiModelProperty("创建时间")
     private String creTime;
    /**
     * 修改时间
     */
     @ApiModelProperty("修改时间")
     private Integer upTime;
    /**
     * 创建人id
     */
     @ApiModelProperty("创建人id")
     private Integer creId;
}
