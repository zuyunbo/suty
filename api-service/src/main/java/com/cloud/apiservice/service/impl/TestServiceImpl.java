package com.cloud.apiservice.service.impl;

import com.example.commoncenter.base.BaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.commoncenter.exception.IllegalParameterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ${comments}
 *
 * @author 2u c1111
 * @since 1.0 2020-06-28
 */
@Service
public class TestServiceImpl implements BaseServiceInterface<Test, TestParam, Long> {


    @Autowired
    TestMapper testMapper;

    @Override
    public int save(Test entity) {
        return testMapper.insert(entity);
    }

    @Override
    public int update(Test entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return testMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return testMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Test getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Test> pageQuery(TestParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> testMapper.listQuery(queryParam));
    }

    @Override
    public List<Test> listQuery(TestParam queryParam) {
        return testMapper.selectAll();
    }
}
