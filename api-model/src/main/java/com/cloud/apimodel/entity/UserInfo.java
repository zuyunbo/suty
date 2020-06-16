package com.cloud.apimodel.entity;
import com.example.commoncenter.base.BaseEntity;
import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Transient;

@Builder
@Entity
@Data
public class UserInfo extends BaseEntity{

	@ApiModelProperty("测试用户名 ")
	@Column(name ="user_name")
	private String userName;

	@ApiModelProperty("测试用户密码")
	@Column(name ="pass_word")
	private Integer passWord;

}

