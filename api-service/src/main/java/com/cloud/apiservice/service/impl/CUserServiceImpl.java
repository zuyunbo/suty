package com.cloud.apiservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.apimodel.param.CUserParam;
import com.cloud.apimodel.entity.CUser;
import com.cloud.apiservice.mapper.CUserMapper;
import com.cloud.apiservice.service.CUserService;
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
 * @since 1.0 2021-01-25
 */
@Service
public class CUserServiceImpl implements CUserService {


    @Autowired
    CUserMapper cUserMapper;

    @Override
    public int save(CUser entity) {
        return cUserMapper.insert(entity);
    }

    @Override
    public int update(CUser entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return cUserMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return cUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CUser getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return cUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<CUser> pageQuery(CUserParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> cUserMapper.listQuery(queryParam));
    }

    @Override
    public List<CUser> listQuery(CUserParam queryParam) {
        return cUserMapper.selectAll();
    }
}
