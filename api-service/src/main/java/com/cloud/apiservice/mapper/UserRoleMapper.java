package com.cloud.apiservice.mapper;


import com.cloud.apimodel.entity.UserRole;
import com.cloud.apimodel.param.UserRoleQueryParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface UserRoleMapper extends Mapper<UserRole> {

    List<UserRole> listQuery(UserRoleQueryParam queryParam);

}

