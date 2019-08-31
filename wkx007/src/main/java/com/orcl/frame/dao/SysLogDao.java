package com.orcl.frame.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orcl.frame.model.SysLogModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:
 */
public interface SysLogDao extends BaseMapper<SysLogModel>{
    @Select("select *from system_log")
    List<SysLogModel> selectAll();
    @Select("select COLUMN_NAME from information_schema.columns where table_name='system_log'")
    List<String> selectAllColumn();
}
