package com.cloud.apimodel.entity;

import com.example.commoncenter.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @ApiModelProperty("测试用户名称")
    @Column(name ="name")
    private String name;

    @ApiModelProperty("测试用户电话")
    @Column(name ="phone")
    private Integer phone;

    @ApiModelProperty("测试身份证号")
    @Column(name ="id_card")
    private Integer idCard;
}
