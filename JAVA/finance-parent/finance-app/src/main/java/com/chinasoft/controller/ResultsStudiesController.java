package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysClient;
import com.chinasoft.mybatis.entity.SysClientExpense;
import com.chinasoft.mybatis.entity.SysExpenseWay;
import com.chinasoft.mybatis.entity.SysWayResultObject;
import com.chinasoft.service.SysAdministrationExpenseService;
import com.chinasoft.service.SysClientExpenseService;
import com.chinasoft.service.SysClientService;
import com.chinasoft.service.SysProjectExpenseService;
import com.chinasoft.vo.output.ResultObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/oms/clients")
public class ResultsStudiesController {

    @Autowired
    private SysClientExpenseService sysClientExpenseService;

    @Autowired
    private SysClientService sysClientService;
    @Autowired
    private SysAdministrationExpenseService sysAdministrationExpenseService;

    @Autowired
    private SysProjectExpenseService sysProjectExpenseService;

    /**
     * 查询客户关怀支出
     * @param sysClientExpense
     * @return
     */
    @MethodDesc(value = "查询客户关怀支出", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("clientExpense:view")
    @RequestMapping("/SelectSysClientExpense")
    public ResultObject SelectSysClientExpense(SysClientExpense sysClientExpense){
        List<SysClientExpense> sysClientExpenses = sysClientExpenseService.selectSysClientExpense();
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysClientExpenses);
        return  resultObject ;
    }

    /**
     * 新增客户
     * @param sysClient
     * @return
     */
    @MethodDesc(value = "新增客户", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("addClient:view")
    @RequestMapping(value = "/addClient")
    public ResultObject addClient(SysClient sysClient) {
        sysClientService.insertSelective(sysClient);
        return new ResultObject();
    }

    /**
     * 查询客户
     * @param name
     * @return
     */
    @MethodDesc(value = "获取客户名",type = MethodDesc.Type.VIEW)
    @RequestMapping(value = "/clientName")
    public ResultObject clientName(String name){
        List<SysClient> sysClient= sysClientService.selectSysClientName(name);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysClient);
        return  resultObject ;
    }

    /**
     * 新增客户关怀支出
     * @param sysWayResultObject
     * @return
     */
    @MethodDesc(value = "添加客户关怀支出", type = MethodDesc.Type.ADD)
    @RequiresPermissions("clientExpense:add")
    @RequestMapping(value ="/add")
    public ResultObject add(SysWayResultObject sysWayResultObject){
        SysClientExpense sysClientExpense = new SysClientExpense();
        sysClientExpense.setCreateName(sysWayResultObject.getCreateName());
        sysClientExpense.setClientName(sysWayResultObject.getClientName());
        sysClientExpense.setType(sysWayResultObject.getType());
        sysClientExpense.setExpense(sysWayResultObject.getExpense());
        sysClientExpense.setTime(sysWayResultObject.getTime());
        sysClientExpense.setDetails(sysWayResultObject.getDetails());

        sysClientExpenseService.insertClientEx(sysClientExpense);
        Long clientId = sysClientExpense.getId();

        String way = sysWayResultObject.getWay();
        String wayExpense = sysWayResultObject.getWayExpense();
        String[] split = way.split(",");
        String[] split1 = wayExpense.split(",");

        for(int i = 0;i<split.length;i++){
            SysExpenseWay sysExpense = new SysExpenseWay();
            sysExpense.setWayType(Integer.valueOf(split[i]));
            sysExpense.setWayExpense(Double.valueOf(split1[i]));
            sysExpense.setClientId((long)clientId);
            sysClientExpenseService.insertExpense(sysExpense);
        }


        return new ResultObject();
    }

    /**
     * 行政支出总金额查询
     * @return
     */
    @MethodDesc(value = "行政支出总金额查询", type = MethodDesc.Type.ADD)
    @RequestMapping("/selectSumExpense")
    public ResultObject selectSumExpense(){
        Integer expense = sysAdministrationExpenseService.selectSumExpense();
        return new ResultObject(expense);
    }

    /**
     * 项目总支出查询
     * @return
     */
    @MethodDesc(value = "项目总支出查询", type = MethodDesc.Type.ADD)
    @RequestMapping("/selectProject")
    public ResultObject selectProject(){
        Integer expense = sysProjectExpenseService.selectProject();
        return new ResultObject(expense);
    }

    /**
     * 客户关怀支出总金额查询
     * @return
     */
    @MethodDesc(value = "客户关怀支出总金额查询", type = MethodDesc.Type.ADD)
    @RequestMapping("/seceltClientSum")
    public ResultObject seceltClientSum(){
        Integer expense = sysClientExpenseService.seceltClientSum();
        return new ResultObject(expense);
    }

}
