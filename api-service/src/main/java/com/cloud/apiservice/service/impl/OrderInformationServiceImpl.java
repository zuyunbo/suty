package com.cloud.apiservice.service.impl;

import com.cloud.apimodel.entity.OrderInformation;
import com.cloud.apimodel.param.OrderInformationQueryParam;
import com.cloud.apiservice.mapper.OrderInformationMapper;
import com.cloud.apiservice.service.OrderInformationService;
import com.example.commoncenter.exception.IllegalParameterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class OrderInformationServiceImpl implements OrderInformationService {

    @Autowired
    OrderInformationMapper orderInformationMapper;

    @Override
    public int save(OrderInformation entity) {
        return orderInformationMapper.insert(entity);
    }

    @Override
    public int update(OrderInformation entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            throw new IllegalParameterException("更新内容id不能为空");
        }
        return orderInformationMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return orderInformationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderInformation getById(Long id) {
        if (id == null || id == 0L) {
            throw new IllegalParameterException("详情id不能为空");
        }
        return orderInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<OrderInformation> pageQuery(OrderInformationQueryParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> orderInformationMapper.listQuery(queryParam));
    }

    @Override
    public List<OrderInformation> listQuery(OrderInformationQueryParam queryParam) {
        return null;
    }
}
