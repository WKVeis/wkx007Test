package com.orcl.frame.controller;

import com.github.pagehelper.PageInfo;
import com.orcl.frame.model.SysLogModel;
import com.orcl.frame.request.SysLogRequest;
import com.orcl.frame.service.impl.SysLogServiceInterfaceImpl;
import com.orcl.frame.utils.ExcelUtils;
import com.orcl.frame.utils.annotation.SysLog;
import com.orcl.frame.utils.common.Constants;
import com.orcl.frame.utils.exception.ProjectException;
import com.orcl.frame.vo.Response;
import com.orcl.frame.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by weikaixiang
 * @date 2019/8/11 0011
 * @DESC:日志管理
 */
@Api(value = "SysLogController", description = "For System's log")
@RestController
@RequestMapping("/v1/sys/log")
public class SysLogController {
    @Autowired
    private SysLogServiceInterfaceImpl serviceInterface;
    @PostMapping("/list")
    @ApiOperation(value = "PageList", notes = "Data pagination of system log")
    public String list(@RequestBody SysLogRequest request) throws Exception{
        Response response = new Response();
        Result result = new Result();
        try {
            PageInfo data = serviceInterface.list(request);
            result.getData().put("data", data);
        } catch (ProjectException e) {
            result.setState(e.getError());
        } catch (Exception e) {
            result.setState(new ProjectException(Constants.Return.SYSTEMLOG_LIST_ERROR, e.getMessage()));
        }
        response.setResult(result);
        return response.toJson();
    }

    @PostMapping("/excel")
    @ApiOperation(value = "excel",notes = "excel all data of log")
    @SysLog("导出日志报表")
    public void excel(HttpServletResponse response) {
        List<String> head = serviceInterface.selectAllColumn();//得到所有的列名，用作表格标题
        List<String> head2 = new LinkedList<>();
       head.forEach(e->{
           if (e.equals("userName")) {
               head2.add(e);
           }
           if (e.equals("operation")) {
               head2.add(e);
           }
           if (e.equals("ip")) {
               head2.add(e);
           }
           if (e.equals("Create_Date")) {
               head2.add(e);
           }
           if (e.equals("description")) {
               head2.add(e);
           }
       });
//        List<String> head2 = new LinkedList<>();
//        head.forEach(e->{
//            head2.addAll(Arrays.asList(e.split("\\,")));
//        });
        List<SysLogModel> list = serviceInterface.selectAll();//得到所有的记录用作表格内容
        List<List<String>> body = new ArrayList<>();
        for (SysLogModel sysLogModel : list) {
            List<String> bodyValue = new ArrayList<>();
            bodyValue.add(sysLogModel.getUserName());
            bodyValue.add(sysLogModel.getOperation());
            bodyValue.add(sysLogModel.getIp());
            bodyValue.add(String.valueOf(sysLogModel.getCreateDate()));
            bodyValue.add(sysLogModel.getDescription());
            body.add(bodyValue);
        }
        String fileName = "qq.xls";
        HSSFWorkbook excel = ExcelUtils.expExcel(head2,body);
        ExcelUtils.outFile(excel,"./"+fileName,response);
    }
}
