package com.cloud.apiservice.mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.UserInfo;

/**
 *
 * @author 2u c1111
 * @since 1.0 2020-06-23
 */
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
	
}
