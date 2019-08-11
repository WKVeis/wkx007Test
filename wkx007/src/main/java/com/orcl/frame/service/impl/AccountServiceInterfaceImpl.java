package com.orcl.frame.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orcl.frame.dao.AccountDao;
import com.orcl.frame.model.Account;
import com.orcl.frame.request.AccountRequest;
import com.orcl.frame.request.LoginRequest;
import com.orcl.frame.service.AccountServiceInterface;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.sun.tools.doclint.Entity.Lambda;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
@Service
public class AccountServiceInterfaceImpl implements AccountServiceInterface {
    @Autowired
    private AccountDao accountDao;
    @Override
    public int add(Account account) {
//        String Id=UUID.randomUUID().toString().replaceAll("-","");
//        account.setId(Id);
        account.setCreateTime(new Date());
        account.setUpdateTime(null);//添加用户不需要更新时间，前端可以不穿这个参数，后台只是暂时写死
        int i = accountDao.insert(account);
        return i;
    }

    @Override
    public PageInfo list(AccountRequest accountRequest) throws ProjectException {
        PageHelper.startPage(accountRequest.getPageNum(), accountRequest.getPageSize());
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(accountRequest.getUserName()), Account::getUserName, accountRequest.getUserName())
                .eq(StringUtils.isNotBlank(accountRequest.getSex()), Account::getSex, accountRequest.getSex())
                .eq(accountRequest.getAge()!=null, Account::getAge, accountRequest.getAge());
        List<Account> list = accountDao.selectList(queryWrapper);
        PageInfo<Account> res = new PageInfo(list);
        return res;
    }

    @Override
    public int update(Account account) throws ProjectException {
        Account old = accountDao.selectById(account.getId());
        if (null == old) {
            throw new ProjectException(Constants.Return.ACCOUNT_FINDACCOUNT_ERROR);
        }
        account.setCreateTime(old.getCreateTime());//用户的创建时间不变，这里暂时写死，前端不用传createTime
        account.setUpdateTime(new Date());
        int res = accountDao.updateById(account);
        return res;
    }

    @Override
    public Account findById(Long id) throws ProjectException {
        Account account = accountDao.selectById(id);
        if (null == account) {
            throw new ProjectException(Constants.Return.ACCOUNT_FINDACCOUNT_ERROR);
        }
        return account;
    }

    @Override
    public Account findLoginAccount(LoginRequest request) throws ProjectException {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(request.getUserName()), Account::getUserName, request.getUserName())
                    .eq(StringUtils.isNotBlank(request.getPassword()),Account::getPassword,request.getPassword());
        Account account = accountDao.selectOne(queryWrapper);
        return account;
    }
}

