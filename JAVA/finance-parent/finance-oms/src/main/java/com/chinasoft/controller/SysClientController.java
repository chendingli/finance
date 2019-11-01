package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysClient;
import com.chinasoft.service.SysClientService;
import com.chinasoft.vo.output.ResultObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Name:WangJinhui
 * Date:2018-12-10
 *
 */

@RestController
@RequestMapping("/api/oms/client")
public class SysClientController {


    @Autowired
    SysClientService sysClientService;

    /**
     * 新增客户
     * @param client
     * @return
     */
    @MethodDesc(value = "添加客户",type = MethodDesc.Type.ADD)
    @RequiresPermissions(value = "client:add")
    @RequestMapping("/add")
    public ResultObject addClient(SysClient client) {

        SysClient sysClient = new SysClient();
        sysClient.setPhone(client.getPhone());
        sysClient = sysClientService.selectOne(sysClient);

        if (sysClient != null) {
            return new ResultObject("-1", "该客户已存在",null);
        }

        sysClientService.insert(client);

        return new ResultObject();
    }

    /**
     * 通过ID查询客户
     * @param id
     * @return
     */
    @MethodDesc(value = "查找单个客户",type = MethodDesc.Type.VIEW)
    @RequiresPermissions(value = "client:view")
    @RequestMapping("/get")
    public ResultObject getClient(Long id) {

        SysClient client = sysClientService.selectByPrimaryKey(id);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(client);

        return resultObject;
    }

    /**
     * 修改客户信息
     * @param client
     * @return
     */
    @MethodDesc(value = "修改客户",type = MethodDesc.Type.UPDATE)
    @RequiresPermissions(value = "client:update")
    @RequestMapping("/update")
    public ResultObject getClient(SysClient client) {
        sysClientService.updateByPrimaryKeySelective(client);

        return new ResultObject();
    }




}
