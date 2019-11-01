package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysClientExpense;
import com.chinasoft.mybatis.entity.SysClientInfo;
import com.chinasoft.mybatis.entity.SysExpense;
import com.chinasoft.mybatis.entity.SysWayResultObject;
import com.chinasoft.service.SysClientExpenseService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/oms/clientExpense")
public class ClientExpenseController {

    @Autowired
    private SysClientExpenseService sysClientExpenseService;
    @MethodDesc(value = "分页查询客户关怀支出列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("clientExpense:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           SysClientExpense sysClientExpense){
        PageInfo page = sysClientExpenseService.findPage(pageNum, pageSize,sysClientExpense);
        return new PageResult(page.getTotal(),page.getList());
    }

    @MethodDesc(value = "添加客户关怀支出", type = MethodDesc.Type.ADD)
    @RequiresPermissions("clientExpense:add")
    @RequestMapping("/add")
    public ResultObject add(SysWayResultObject sysWayResultObject){


        SysClientExpense sysClientExpense = new SysClientExpense();
        sysClientExpense.setCreateName(sysWayResultObject.getCreateName());
        sysClientExpense.setClientName(sysWayResultObject.getClientName());
        sysClientExpense.setType(sysWayResultObject.getType());
        sysClientExpense.setExpense(sysWayResultObject.getExpense());
        sysClientExpense.setTime(sysWayResultObject.getTime());
        sysClientExpense.setDetails(sysWayResultObject.getDetails());

        //新增客户支出项
        sysClientExpenseService.insertClientEx(sysClientExpense);
        //获取新增ID
        Long clientId = sysClientExpense.getId();
        //获取支出途径和对应支出金额
        String way = sysWayResultObject.getWay();
        String wayExpense = sysWayResultObject.getWayExpense();
        String[] split = way.split(",");
        String[] split1 = wayExpense.split(",");
        //新增多条支出途径项
        for(int i = 0;i<split.length;i++){
            SysExpense sysExpense = new SysExpense();
            sysExpense.setWayType(Integer.valueOf(split[i]));
            sysExpense.setWayExpense(Double.valueOf(split1[i]));
            sysExpense.setClientId((long)clientId);
            sysClientExpenseService.insertExpense(sysExpense);
        }

        return new ResultObject();
    }

    @MethodDesc(value = "获取客户名",type = MethodDesc.Type.VIEW)
    @RequestMapping("/clientName")
    public ResultObject findClientName(){
        List<String> clientName = sysClientExpenseService.findClientName();
        return new ResultObject(clientName);
    }

    @MethodDesc(value = "查询单条客户关怀支出", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("clientExpense:view")
    @RequestMapping("/get")
    public ResultObject get(Long id){
        SysClientExpense sysProjectExpense = sysClientExpenseService.get(id);
        //根据ID查找客户对应的多条支出途径
        List<SysExpense> expense = sysClientExpenseService.getExpense(id);
        SysWayResultObject result = new SysWayResultObject();
        result.setClientId(sysProjectExpense.getId());
        result.setClientName(sysProjectExpense.getClientName());
        result.setCreateName(sysProjectExpense.getCreateName());
        result.setType(sysProjectExpense.getType());
        result.setExpense(sysProjectExpense.getExpense());
        result.setTime(sysProjectExpense.getTime());
        result.setDetails(sysProjectExpense.getDetails());
        result.setList(expense);
        return new ResultObject(result);
    }

    @RequestMapping("/update")
    public ResultObject update(SysClientExpense sysClientExpense){

        sysClientExpenseService.update(sysClientExpense);
        return new ResultObject();
    }
}
