package com.chinasoft.controller;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysPersonl;
import com.chinasoft.service.SysPersonlService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Auther: 汪毅
 * @Date: 2018/6/6 10:03
 * @Description:
 */
@RestController
@RequestMapping("/api/oms/personl")
public class PersonlController {

    @Autowired
    private SysPersonlService sysPersonlService;

   //导出表格
    @MethodDesc(value = "导出表格", type = MethodDesc.Type.VIEW)
    //表格导出权限
    @RequiresPermissions("personl:downl")
    @RequestMapping("/exportStuInfoExcel")
    public void exportStuInfoExcel(HttpServletResponse response) throws IOException {
        //设置响应输出的头类型
        String str = "aax";
        sysPersonlService.downExcle(response,str);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(str+".xlsx");

    }
    //查询角色
    @MethodDesc(value = "查询角色列表", type = MethodDesc.Type.VIEW)
    //查询角色权限
    @RequiresPermissions("personl:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           SysPersonl sysperonl) {
        System.out.println(sysperonl);

        System.out.println("***********************************************************************************************************************");
        PageInfo<SysPersonl> pageInfo = sysPersonlService.findPage(pageNum, pageSize,sysperonl);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    //得到指定的的角色数据
    @MethodDesc(value = "获取指定角色", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("personl:view")
    @RequestMapping("/get")
    public ResultObject get(Long id) {

        SysPersonl sysPersonl = sysPersonlService.get(id);

        return new ResultObject(sysPersonl);
    }

    //添加角色
    @MethodDesc(value = "添加角色", type = MethodDesc.Type.ADD)
    //角色增加权限
    @RequiresPermissions("personl:add")
    @RequestMapping("/add")
    public ResultObject add(SysPersonl sysPersonl) {

        SysPersonl personl = new SysPersonl();
        personl.setUnmberId(sysPersonl.getUnmberId());
        personl = sysPersonlService.selectOne(personl);
        //判断工号是否重复
        if (personl!= null) {
            return new ResultObject("-1", "该工号已存在",null);
        }
        sysPersonlService.insertSelective(sysPersonl);

        return new ResultObject();
    }

    //员工工号自动填充加一
    @RequestMapping("/getNumber")
    public ResultObject getUnmberId(){
        Integer number = sysPersonlService.getUnmberId();
        return new ResultObject(number);
    }

    //更新角色信息
    @MethodDesc(value = "更新角色", type = MethodDesc.Type.UPDATE)
    //角色更新权限
    @RequiresPermissions("personl:update")
    @RequestMapping("/update")
    @ResponseBody
    public ResultObject update(@RequestBody SysPersonl sysPersonl) {
        SysPersonl personl = new SysPersonl();
        personl.setUnmberId(sysPersonl.getUnmberId());
        personl = sysPersonlService.selectOne(personl);
        //判断工号是否重复
        if (personl!= null) {
            return new ResultObject("-1", "该工号已存在",null);
        }

        sysPersonlService.updateByPrimaryKeySelective(sysPersonl);
        return new ResultObject();

    }
}
