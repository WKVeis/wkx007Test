package com.orcl.frame.service.impl;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.dao.RoleDao;
import com.orcl.frame.model.Role;
import com.orcl.frame.request.RoleRequest;
import com.orcl.frame.service.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/8/23.
 */
@Service
public class RoleServiceInterfaceImpl implements RoleServiceInterface {
    @Autowired
    private RoleDao dao;
    @Override
    public int add(Role role) {
        //        String Id=UUID.randomUUID().toString().replaceAll("-","");
//        account.setId(Id);
        role.setCreateTime(new Date());
        role.setUpdateTime(null);//添加用户不需要更新时间，前端可以不穿这个参数，后台只是暂时写死
        int i = dao.insert(role);
        return i;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public PageInfo<Role> list(RoleRequest request) {
        return null;
    }

    @Override
    public int del(Long id) {
        return 0;
    }
}
