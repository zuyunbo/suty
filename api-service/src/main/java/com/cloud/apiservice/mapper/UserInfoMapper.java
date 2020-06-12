package com.cloud.apiservice.mapper;


import com.cloud.apimodel.entity.UserInfo;
import com.cloud.apimodel.param.UserInfoQueryParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserInfoMapper extends  Mapper<UserInfo>  {

 List<UserInfo> listQuery(UserInfoQueryParam queryParam);

}

