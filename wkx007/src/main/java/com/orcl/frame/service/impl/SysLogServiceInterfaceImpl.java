package com.orcl.frame.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orcl.frame.dao.SysLogDao;
import com.orcl.frame.model.Account;
import com.orcl.frame.model.SysLogModel;
import com.orcl.frame.request.SysLogRequest;
import com.orcl.frame.service.SysLogServiceInterface;
import com.orcl.frame.utils.exception.ProjectException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:
 */
@Service
public class SysLogServiceInterfaceImpl implements SysLogServiceInterface {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public int insert(SysLogModel sysLog) {
        String Id= UUID.randomUUID().toString().replaceAll("-","");
        sysLog.setId(Id);
        return sysLogDao.insert(sysLog);
    }

    @Override
    public PageInfo list(SysLogRequest request) throws ProjectException {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        LambdaQueryWrapper<SysLogModel> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(request.getUserName()), SysLogModel::getUserName, request.getUserName())
                .eq(StringUtils.isNotBlank(request.getIp()), SysLogModel::getIp, request.getIp());
        List<SysLogModel> list = sysLogDao.selectList(queryWrapper);
        PageInfo<Account> res = new PageInfo(list);
        return res;
    }
}

