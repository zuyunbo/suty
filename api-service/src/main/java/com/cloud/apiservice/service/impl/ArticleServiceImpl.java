package com.cloud.apiservice.service.impl;

import com.example.commoncenter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloud.apimodel.param.ArticleParam;
import com.cloud.apimodel.entity.Article;
import com.cloud.apiservice.mapper.ArticleMapper;
import com.cloud.apiservice.service.ArticleService;
import com.example.commoncenter.exception.IllegalParameterException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



import java.util.Map;

/**
 * ${comments}
 *
 * @author 2u c1111
 * @since 1.0 2020-12-04
 */
@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int save(Article entity) {
        Authentication user = User.getUser();
        return articleMapper.insert(entity);
    }

    @Override
    public int update(Article entity) {
        if (entity.getId() == null || entity.getId() == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("更新内容id不能为空");
        }

        return articleMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("删除操作id不能为空");
        }
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Article getById(Long id) {
        if (id == null || id == 0L) {
            // 自己定义的异常方法
            throw new IllegalParameterException("详情id不能为空");
        }

        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Article> pageQuery(ArticleParam queryParam) {
        return PageHelper.startPage(queryParam.getPn(), queryParam.getPs())
                .doSelectPageInfo(() -> articleMapper.listQuery(queryParam));
    }

    @Override
    public List<Article> listQuery(ArticleParam queryParam) {
        return articleMapper.selectAll();
    }
}
