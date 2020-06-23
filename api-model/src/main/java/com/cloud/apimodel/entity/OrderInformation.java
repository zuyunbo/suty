package com.cloud.apimodel.entity;

import com.example.commoncenter.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 订单信息实体
 */
@Builder
@Entity
@Data
public class OrderInformation extends BaseEntity {

    @ApiModelProperty("用户id")
    @Column(name ="mid")
    private Integer mid;

    @ApiModelProperty("订单来源（1：兔子分期）")
    @Column(name ="order_source")
    private Integer orderSource;

    @ApiModelProperty("订单状态 （1：授权激活）")
    @Column(name ="state")
    private Integer state;

    @ApiModelProperty("备注")
    @Column(name ="remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @Column(name ="creat_time")
    private Date creatTime;

    @JsonIgnore
    @ApiModelProperty("测试用户名称")
    @Transient
    private String name;

    @JsonIgnore
    @ApiModelProperty("测试用户电话")
    @Transient
    private Integer phone;

    @JsonIgnore
    @ApiModelProperty("测试身份证号")
    @Transient
    private Integer idCard;
}
