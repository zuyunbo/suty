package com.cloud.apiservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.apimodel.param.SysUserParam;
import com.cloud.apimodel.entity.SysUser;
import com.cloud.apiservice.mapper.SysUserMapper;
import com.cloud.apiservice.service.SysUserService;
import com.example.commoncenter.exception.IllegalParameterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.stereotype.Service;



import java.util.Map;

/**
 * ${comments}
 *
 * @author 2u c1111
 * @since 1.0 2020-11-16
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public int save(SysUser entity) {
        return sysUserMapper.insert(entity);
    }

    @Override
    public int update(SysUser entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return sysUserMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysUser getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<SysUser> pageQuery(SysUserParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> sysUserMapper.listQuery(queryParam));
    }

    @Override
    public List<SysUser> listQuery(SysUserParam queryParam) {
        return sysUserMapper.selectAll();
    }


    @Override
    public SysUser selectByUserName(String userName){
      return   sysUserMapper.selectByUserName(userName);
    }

}
