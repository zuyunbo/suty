package com.cloud.apiservice.service.impl;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.apimodel.param.AccountParam;
import com.cloud.apimodel.entity.Account;
import com.cloud.apiservice.mapper.AccountMapper;
import com.cloud.apiservice.service.AccountService;
import com.example.commoncenter.exception.IllegalParameterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.stereotype.Service;



import java.util.Map;

/**
 * ${comments}
 *
 * @author admin c1111
 * @since 1.0 2020-07-02
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    AccountMapper accountMapper;

    @Override
    public int save(Account entity) {
        return accountMapper.insert(entity);
    }

    @Override
    public int update(Account entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return accountMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Account getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Account> pageQuery(AccountParam queryParam) {
        PageHelper.startPage(queryParam.getPn(), queryParam.getPs());
        List<Account> accounts = accountMapper.selectAll();
        return new PageInfo<>(accounts);

    }




    @Override
    public List<Account> listQuery(AccountParam queryParam) {
        return accountMapper.selectAll();
    }

    @Override
    public void getExcted() {
        throw new IllegalParameterException("详情id不能为空");
    }
}
