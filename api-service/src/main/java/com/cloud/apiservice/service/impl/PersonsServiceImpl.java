package com.cloud.apiservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.apimodel.param.PersonsParam;
import com.cloud.apimodel.entity.Persons;
import com.cloud.apiservice.mapper.PersonsMapper;
import com.cloud.apiservice.service.PersonsService;
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
 * @since 1.0 2020-09-02
 */
@Service
public class PersonsServiceImpl implements PersonsService {


    @Autowired
    PersonsMapper personsMapper;

    @Override
    public int save(Persons entity) {
        return personsMapper.insert(entity);
    }

    @Override
    public int update(Persons entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return personsMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return personsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Persons getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return personsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Persons> pageQuery(PersonsParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> personsMapper.listQuery(queryParam));
    }

    @Override
    public List<Persons> listQuery(PersonsParam queryParam) {
        return personsMapper.selectAll();
    }
}
