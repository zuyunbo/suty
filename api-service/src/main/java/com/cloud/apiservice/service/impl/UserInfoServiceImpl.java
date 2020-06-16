package com.cloud.apiservice.service.impl;

import com.cloud.apimodel.entity.UserInfo;
import com.cloud.apimodel.param.UserInfoQueryParam;
import com.cloud.apiservice.mapper.UserInfoMapper;
import com.cloud.apiservice.service.UserInfoService;
import com.example.commoncenter.exception.IllegalParameterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int save(UserInfo entity) {
        return userInfoMapper.insert(entity);
    }

    @Override
    public int update(UserInfo entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return userInfoMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }


        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserInfo getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<UserInfo> pageQuery(UserInfoQueryParam queryParam) {
        PageHelper.startPage(queryParam.getPn(), queryParam.getPs());
        List<UserInfo> list = userInfoMapper.listQuery(queryParam);
        PageInfo<UserInfo> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<UserInfo> listQuery(UserInfoQueryParam queryParam) {
        return userInfoMapper.listQuery(queryParam);
    }
}

