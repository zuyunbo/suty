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
public class TestCeshiServiceImpl implements BaseServiceInterface<TestCeshi, TestCeshiParam, Long> {


    @Autowired
    TestCeshiMapper testCeshiMapper;

    @Override
    public int save(TestCeshi entity) {
        return testCeshiMapper.insert(entity);
    }

    @Override
    public int update(TestCeshi entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return testCeshiMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return testCeshiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TestCeshi getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return testCeshiMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<TestCeshi> pageQuery(TestCeshiParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> testCeshiMapper.listQuery(queryParam));
    }

    @Override
    public List<TestCeshi> listQuery(TestCeshiParam queryParam) {
        return testCeshiMapper.selectAll();
    }
}
