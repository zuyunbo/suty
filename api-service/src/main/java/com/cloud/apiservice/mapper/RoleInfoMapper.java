package com.cloud.apiservice.mapper;


import com.cloud.apimodel.entity.RoleInfo;
import com.cloud.apimodel.param.RoleInfoQueryParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface RoleInfoMapper extends Mapper<RoleInfo> {

    List<RoleInfo> listQuery(RoleInfoQueryParam queryParam);

}

