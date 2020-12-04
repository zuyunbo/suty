package com.cloud.apiservice.mapper;


import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.Article;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.apimodel.param.ArticleParam;


/**
 *
 * @author 2u c1111
 * @since 1.0 2020-12-04
 */
@Repository
public interface ArticleMapper extends Mapper<Article> {


    List<Article> listQuery(ArticleParam queryParam);

}
