package com.orcl.frame.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orcl.frame.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */
public interface RoleDao extends BaseMapper<Role> {
    @Select("select DISTINCT description from role;")
    List<String> selectAll();
}
