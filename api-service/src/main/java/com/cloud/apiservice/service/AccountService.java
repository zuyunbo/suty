package com.cloud.apiservice.service;

import com.example.commoncenter.base.BaseServiceInterface;
import com.cloud.apimodel.param.AccountParam;
import com.cloud.apimodel.entity.Account;

/**
 * ${comments}
 *
 * @author admin c1111
 * @since 1.0 2020-07-02
 */
public interface AccountService extends BaseServiceInterface<Account, AccountParam,Long> {

    void getExcted();

}
