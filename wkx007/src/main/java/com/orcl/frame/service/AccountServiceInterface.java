package com.orcl.frame.service;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.Account;
import com.orcl.frame.request.AccountRequest;
import com.orcl.frame.request.LoginRequest;
import com.orcl.frame.utils.exception.ProjectException;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
public interface AccountServiceInterface  {
    int add(Account account) throws ProjectException;

    PageInfo list(AccountRequest accountRequest) throws ProjectException;

    int update(Account account) throws ProjectException;

    Account findById(Long id) throws ProjectException;

    Account findLoginAccount(LoginRequest request) throws ProjectException;

    int del(String id);
}
