package com.cloud.apiservice.mapper;


import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.CUser;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.apimodel.param.CUserParam;


/**
 *
 * @author 2u c1111
 * @since 1.0 2021-01-25
 */
@Repository
public interface CUserMapper extends Mapper<CUser> {


    List<CUser> listQuery(CUserParam queryParam);

}
