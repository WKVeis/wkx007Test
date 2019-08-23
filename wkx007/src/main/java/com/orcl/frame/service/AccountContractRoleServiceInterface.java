package com.orcl.frame.service;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.AccountContactRole;
import com.orcl.frame.request.AccountContractRoleRequest;

/**
 * Created by Administrator on 2019/8/23.
 */
public interface AccountContractRoleServiceInterface {

    int add(AccountContactRole accountContactRole);

    int update(AccountContactRole accountContactRole);

    PageInfo<AccountContactRole> list(AccountContractRoleRequest request);

    int del(Long id);
}
