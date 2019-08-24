package com.orcl.frame.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.Role;
import com.orcl.frame.request.RoleRequest;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */
public interface RoleServiceInterface {
    int add(Role role);

    int update(Role role);

    PageInfo<Role> list(RoleRequest request);

    int del(Long id);

    List<String> select();

}
