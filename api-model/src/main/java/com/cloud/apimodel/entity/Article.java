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
 * @since 1.0 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@Api(description = "article")
public class Article extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 
     */
     @ApiModelProperty("")
     private String title;
    /**
     * 
     */
     @ApiModelProperty("")
     private String content;
    /**
     * 
     */
     @ApiModelProperty("")
     private Integer articleType;
    /**
     * 
     */
     @ApiModelProperty("")
     private Integer createId;
    /**
     * 
     */
     @ApiModelProperty("")
     private String createTime;
    /**
     * 
     */
     @ApiModelProperty("")
     private Integer updateId;
    /**
     * 
     */
     @ApiModelProperty("")
     private String updateTime;
    /**
     * 
     */
     @ApiModelProperty("")
     private Integer attachmentId;
}
