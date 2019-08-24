package com.orcl.frame.service.impl;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.dao.AccountContactRoleDao;
import com.orcl.frame.model.AccountContactRole;
import com.orcl.frame.request.AccountContractRoleRequest;
import com.orcl.frame.service.AccountContractRoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/8/24.
 */
@Service
public class AccountContactRoleServiceImpl implements AccountContractRoleServiceInterface {
    @Autowired
    private AccountContactRoleDao dao;
    @Override
    public int add(AccountContactRole accountContactRole) {
        //String Id=UUID.randomUUID().toString().replaceAll("-","");
        //account.setId(Id);
        accountContactRole.setCreateTime(new Date());
        accountContactRole.setUpdateTime(null);//添加用户不需要更新时间，前端可以不穿这个参数，后台只是暂时写死
        int i = dao.insert(accountContactRole);
        return i;
    }

    @Override
    public int update(AccountContactRole accountContactRole) {
        return 0;
    }

    @Override
    public PageInfo<AccountContactRole> list(AccountContractRoleRequest request) {
        return null;
    }

    @Override
    public int del(Long id) {
        return 0;
    }
}
