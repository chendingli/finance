package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.*;
import com.chinasoft.service.*;
import com.chinasoft.vo.output.ResultObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oms/spending")
public class SpendingController {

    @Autowired
    private SysAdministrationExpenseService sysAdministrationExpenseService;
    @Autowired
    private SysProjectExpenseService sysProjectExpenseService;
    @Autowired
    private SysLettersService sysLettersService;

    @Autowired
    private SysSpendingService sysSpendingService;
    /**
     * 查询行政支出
     * @param sysAdministrationExpense
     * @return
     */
    @MethodDesc(value = "查询行政支出 ", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("administrationExpense:view")
    @RequestMapping("/selectAdministrationExpense")
    public ResultObject selectAdministrationExpense(SysAdministrationExpense sysAdministrationExpense){
        List<SysAdministrationExpense> sysAdministration = sysAdministrationExpenseService.selectAdministrationExpense();
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysAdministration);
        return  resultObject ;
    }


    /**
     * 查询项目支出
     * @param sysProjectExpense
     * @return
     */
    @MethodDesc(value = "查询项目支出", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("projectExpense:view")
    @RequestMapping("/selectProjectExpense")
    public ResultObject selectProjectExpense(SysProjectExpense sysProjectExpense){
        List<SysProjectExpense> sysProject = sysProjectExpenseService.selectProjectExpense();
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysProject);
        return  resultObject ;
    }


    /**
     * 行政支出添加
     * @param sysAdministrationExpense
     * @return
     */
    @MethodDesc(value = "添加行政支出", type = MethodDesc.Type.ADD)
    @RequiresPermissions("administrationExpense:add")
    @RequestMapping("/addInstr")
    public ResultObject addInstr(SysAdministrationExpense sysAdministrationExpense){
        sysAdministrationExpenseService.insertAdministratinoExpense(sysAdministrationExpense);
        return new ResultObject();
    }


    /**
     * 项目支出添加
     * @param sysProjectExpense
     * @return
     */
    @MethodDesc(value = "添加项目支出项", type = MethodDesc.Type.ADD)
    @RequiresPermissions("projectExpense:add")
    @RequestMapping("/addProject")
    public ResultObject addProject(SysProjectExpense sysProjectExpense){
       /* String contractName = sysProjectExpenseService.findContractNameByNumber(sysProjectExpense.getContractNumber());
        sysProjectExpense.setProjectName(contractName);
        sysProjectExpenseService.insertProjectExpense(sysProjectExpense);*/
       sysProjectExpenseService.insertProjectExpense(sysProjectExpense);
        return new ResultObject();
    }


    /**
     * 查询项目名称
     * @return
     */
    @MethodDesc(value = "获取合同", type = MethodDesc.Type.VIEW)
    @RequestMapping("/contract")
    public ResultObject findContract(){
        List<String> name = sysProjectExpenseService.findContract();
        return new ResultObject(name);
    }


    /**
     * 通过项目名称获取项目编号
     * @param name
     * @return
     */
    @MethodDesc(value = "根据合同名获取合同编号", type = MethodDesc.Type.VIEW)
    @RequestMapping("/getNumber")
    @ResponseBody
    public ResultObject findNumberByName(@RequestBody String name){
        Integer number = sysProjectExpenseService.findNumberByName(name);
        return new ResultObject(number);
    }

    @MethodDesc(value = "查询合同的数量,总金额,已回款,待回款", type = MethodDesc.Type.VIEW)
    @RequestMapping("/selectSunContract")
    public ResultObject selectSunContract(SysLetters sysLetters){
        List<SysLetters> sysContract = sysLettersService.selectSumContract(sysLetters);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysContract);
        return  resultObject ;
    }

    @MethodDesc(value = "查询各项支出金额", type = MethodDesc.Type.VIEW)
    @RequestMapping("/selectSumTack")
    public ResultObject selectSumTack(SysSpending sysSpending){
        List<SysSpending> sysContract = sysSpendingService.selectSumTack(sysSpending);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysContract);
        return  resultObject ;
    }

    @MethodDesc(value = "查询支出总金额", type = MethodDesc.Type.VIEW)
    @RequestMapping("/selectTotal")
    public ResultObject selectTotal(){
        Double sysContract = sysSpendingService.selectTotal();
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysContract);
        return  resultObject ;
    }

    @MethodDesc(value = "查询资金流水", type = MethodDesc.Type.VIEW)
    @RequestMapping("/selectRunn")
    public ResultObject selectRunn(){
        Double sysContract = sysSpendingService.selectRunn();
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysContract);
        return  resultObject ;
    }

}
