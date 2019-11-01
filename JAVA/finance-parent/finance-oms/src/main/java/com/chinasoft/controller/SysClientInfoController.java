package com.chinasoft.controller;


import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysClient;
import com.chinasoft.mybatis.entity.SysClientInfo;
import com.chinasoft.service.SysClientInfoService;
import com.chinasoft.service.SysClientService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Name:WangJinhui
 * Date:2018-12-10
 */

@RestController
@RequestMapping("/api/oms/client")
public class SysClientInfoController {

    @Autowired
    private SysClientInfoService sysClientInfoService;

    @Autowired
    private SysClientService sysClientService;


    /**
     * 查询所有客户信息：
     *      根据所有客户查询客户关怀总支出、合同总金额、关联合同数量
     * @param pageNum
     * @param pageSize
     * @param clientInfo  前台传入的需要模糊查询的条件，若为""则忽略
     * @return
     */
    @MethodDesc(value = "查找所有客户",type = MethodDesc.Type.VIEW)
    @RequiresPermissions(value = "client:view")
    @RequestMapping("/list")
    public PageResult clientInfoList(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10")Integer pageSize,
                                     SysClientInfo clientInfo) {
        String name = clientInfo.getName().trim();
        clientInfo.setName(name);
        clientInfo.setCreateName(name);
        clientInfo.setPhone(name);
        PageInfo<SysClientInfo> pageInfo = sysClientInfoService.findPage(pageNum, pageSize, clientInfo);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }

}
