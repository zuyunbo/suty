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
 * @since 1.0 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Api(description = "persons")
public class Persons extends BaseEntity {
	private static final long serialVersionUID = 1L;

}
