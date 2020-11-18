package com.cloud.apiservice.mapper;


import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.SysUser;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.apimodel.param.SysUserParam;


/**
 *
 * @author 2u c1111
 * @since 1.0 2020-11-16
 */
@Repository
public interface SysUserMapper extends Mapper<SysUser> {


    List<SysUser> listQuery(SysUserParam queryParam);

    SysUser selectByUserName(String userName);

    SysUser selectByPassWord(String passWord);



}
