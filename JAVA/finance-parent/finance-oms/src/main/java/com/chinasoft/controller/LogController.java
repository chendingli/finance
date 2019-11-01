package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysLog;
import com.chinasoft.service.SysLogService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/9 16:47
 * @Description:
 */
@RestController
@RequestMapping("/api/oms/log")
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 分页查询日志列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @MethodDesc(value = "分页查询日志列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("log:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           SysLog log) {

        PageInfo<SysLog> pageInfo = sysLogService.findPage(pageNum,pageSize,log);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 查询日志详情
     * @param id
     * @return
     */
    @MethodDesc(value = "查询日志详情", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("log:view")
    @RequestMapping("/get")
    public ResultObject get(Long id) {
        SysLog log = sysLogService.get(id);
        return new ResultObject(log);
    }


}
