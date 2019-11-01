package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysProjectExpense;
import com.chinasoft.service.SysProjectExpenseService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/oms/projectExpense")
public class ProjectExpenseController {

    @Autowired
    private SysProjectExpenseService sysProjectExpenseService;

    @MethodDesc(value = "分页查询项目支出列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("projectExpense:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           SysProjectExpense sysProjectExpense){
        PageInfo page = sysProjectExpenseService.findPage(pageNum, pageSize,sysProjectExpense);
        return new PageResult(page.getTotal(),page.getList());
    }

    @MethodDesc(value = "添加项目支出项", type = MethodDesc.Type.ADD)
    @RequiresPermissions("projectExpense:add")
    @RequestMapping("/add")
    public ResultObject add(SysProjectExpense sysProjectExpense){
        //根据编号获取合同名
        String contractName = sysProjectExpenseService.findContractNameByNumber(sysProjectExpense.getContractNumber());
        sysProjectExpense.setProjectName(contractName);
        sysProjectExpenseService.insertProjectExpense(sysProjectExpense);
        return new ResultObject();
    }

    public String findClientName(Integer number){
        return sysProjectExpenseService.findContractNameByNumber(number);
    }

    @MethodDesc(value = "根据ID查询单条项目支出", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("projectExpense:view")
    @RequestMapping("/get")
    public ResultObject get(Long id){
        SysProjectExpense sysProjectExpense = sysProjectExpenseService.get(id);
        return new ResultObject(sysProjectExpense);
    }

    @MethodDesc(value = "修改项目支出列表", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("projectExpense:update")
    @RequestMapping("/update")
    public ResultObject update(SysProjectExpense sysProjectExpense){

        sysProjectExpenseService.update(sysProjectExpense);
        return new ResultObject();
    }

    @MethodDesc(value = "获取合同", type = MethodDesc.Type.VIEW)
    @RequestMapping("/contract")
    public ResultObject findContract(){
        List<String> name = sysProjectExpenseService.findContract();
        return new ResultObject(name);
    }

    @MethodDesc(value = "根据合同名获取合同编号", type = MethodDesc.Type.VIEW)
    @RequestMapping("/getNumber")
    @ResponseBody
    public ResultObject findNumberByName(@RequestBody String name){
        Integer number = sysProjectExpenseService.findNumberByName(name);
        return new ResultObject(number);
    }
}
