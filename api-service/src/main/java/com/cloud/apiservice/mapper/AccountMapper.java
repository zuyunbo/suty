package com.cloud.apiservice.mapper;


import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.Account;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.apimodel.param.AccountParam;


/**
 *
 * @author admin c1111
 * @since 1.0 2020-07-02
 */
@Repository
public interface AccountMapper extends Mapper<Account> {


    List<Account> listQuery(AccountParam queryParam);

}
