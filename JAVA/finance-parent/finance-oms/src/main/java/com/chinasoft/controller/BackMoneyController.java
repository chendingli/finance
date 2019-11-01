package com.chinasoft.controller;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysBackMoney;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysBackMoneyService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/oms/backMoney")
public class BackMoneyController {

    @Autowired
    private SysBackMoneyService sysBackMoneyService;

    @MethodDesc(value = "查询回款列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("backMoney:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           SysBackMoney sysBackMoney) {
        
        PageInfo<SysBackMoney> pageInfo = sysBackMoneyService.findPage(pageNum,pageSize,sysBackMoney);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 回款
     */
    @MethodDesc(value = "回款", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("backMoney:back")
    @RequestMapping("/back")
    public ResultObject back(SysBackMoney sysBackMoney) {

        List<SysBackMoney> a = sysBackMoneyService.selectBackMoneyByNo(sysBackMoney.getNumber());
        SysBackMoney sysBackMoney2 = a.get(0);
        sysBackMoneyService.updateBack(sysBackMoney2.getNumber());

        return new ResultObject(null);
    }

    /**
     * 开票
     */
    @MethodDesc(value = "开票", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("backMoney:invoice")
    @RequestMapping("/invoice")
    public ResultObject invoice(SysBackMoney sysBackMoney) {

        sysBackMoneyService.updateInvoice(sysBackMoney.getAddress(),sysBackMoney.getNumber());

        return new ResultObject(null);
    }

}
