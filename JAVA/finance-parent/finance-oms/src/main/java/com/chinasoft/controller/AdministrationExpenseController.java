package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysAdministrationExpense;
import com.chinasoft.service.SysAdministrationExpenseService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oms/administrationExpense")
public class AdministrationExpenseController {

    @Autowired
    private SysAdministrationExpenseService sysAdministrationExpenseService;
    @MethodDesc(value = "分页查询行政支出列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("administrationExpense:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           SysAdministrationExpense sysAdministrationExpense){


        PageInfo page = sysAdministrationExpenseService.findPage(pageNum, pageSize,sysAdministrationExpense);
        return new PageResult(page.getTotal(),page.getList());
    }

    @MethodDesc(value = "添加行政支出", type = MethodDesc.Type.ADD)
    @RequiresPermissions("administrationExpense:add")
    @RequestMapping("/add")
    public ResultObject add(SysAdministrationExpense sysAdministrationExpense){
        sysAdministrationExpenseService.insertAdministratinoExpense(sysAdministrationExpense);
        return new ResultObject();
    }

    @MethodDesc(value = "获取单条行政支出", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("administrationExpense:view")
    @RequestMapping("/get")
    public ResultObject get(Long id){
        SysAdministrationExpense sysAdministrationExpense = sysAdministrationExpenseService.get(id);
        return new ResultObject(sysAdministrationExpense);
    }

    @MethodDesc(value = "修改行政支出", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("administrationExpense:update")
    @RequestMapping("/update")
    public ResultObject update(SysAdministrationExpense sysAdministrationExpense){

        sysAdministrationExpenseService.update(sysAdministrationExpense);
        return new ResultObject();
    }
}
