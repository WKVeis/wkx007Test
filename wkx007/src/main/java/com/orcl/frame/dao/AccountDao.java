package com.orcl.frame.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orcl.frame.model.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
public interface AccountDao extends BaseMapper<Account> {
    @Select("select *from ACCOUNT where USERNAME=#{userName} AND SEX=#{sex}")
//    @Select("SELECT"
//            +"*FROM ACCOUNT"
//            +"WHERE USERNAME=#{userName}"
//            +" AND SEX=#{sex}")
    Account finBytwo(@Param("userName") String userName, @Param("sex") String sex);
}
