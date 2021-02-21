package com.cloud.apiservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.apimodel.param.CApplicationParam;
import com.cloud.apimodel.entity.CApplication;
import com.cloud.apiservice.mapper.CApplicationMapper;
import com.cloud.apiservice.service.CApplicationService;
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
 * @since 1.0 2021-01-27
 */
@Service
public class CApplicationServiceImpl implements CApplicationService {


    @Autowired
    CApplicationMapper cApplicationMapper;

    @Override
    public int save(CApplication entity) {
        return cApplicationMapper.insert(entity);
    }

    @Override
    public int update(CApplication entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return cApplicationMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return cApplicationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CApplication getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return cApplicationMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<CApplication> pageQuery(CApplicationParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> cApplicationMapper.listQuery(queryParam));
    }

    @Override
    public List<CApplication> listQuery(CApplicationParam queryParam) {
        return cApplicationMapper.selectAll();
    }
}
