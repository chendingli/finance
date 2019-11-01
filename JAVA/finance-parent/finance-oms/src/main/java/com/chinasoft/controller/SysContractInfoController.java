package com.chinasoft.controller;

import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysBackMoney;
import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysContractInfo;
import com.chinasoft.service.SysContractInfoService;
import com.chinasoft.service.SysContractService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Name:chendingli
 * Date:2018-12-10
 */

@RestController
@RequestMapping("/api/oms/contract")
public class SysContractInfoController {

    @Autowired
    private SysContractInfoService sysContractInfoService;

    @Autowired
    private SysContractService sysContractService;

   //文件上传的本地上传地址和远程服务器地址，在application-dev.properties配置文件中配置
    @Value("${file.uploadDir}")
    private String fileRootPath;

    @Value("${file.accessPath}")
    private String url;


    /**
     * 合同列表：
     *      根据合同编号查找关联客户、合同金额、项目总支出、回款信息
     * @param pageNum
     * @param pageSize
     * @param sysContractInfo  前台传入的需要模糊查询的参数
     * @return
     */
    @MethodDesc(value = "查找所有合同",type = MethodDesc.Type.VIEW)
    @RequiresPermissions(value = "contract:view")
    @RequestMapping("/list")
    public PageResult clientInfoList(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10")Integer pageSize,
                                     SysContractInfo sysContractInfo) {
//      模糊查询
        String name = sysContractInfo.getName().trim();
        sysContractInfo.setName(name);
        sysContractInfo.setAssociatedCustomers(name);
        sysContractInfo.setType(name);
//      合同编号为Integer，若前台传入的值是null、""、非数字类型的字符串，则不可赋值给合同编号
        if (name != null && !"".equals(name) && name.matches("^[0-9]*$")) {
            Integer number = Integer.valueOf(name) ;
            sysContractInfo.setNumber(number);
        }
        PageInfo<SysContractInfo> pageInfo = sysContractInfoService.findPage(pageNum, pageSize, sysContractInfo);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }

    /**
     * 获取合同编号
     * @return
     */

    @MethodDesc(value = "获取合同编号",type = MethodDesc.Type.VIEW)
    @RequiresPermissions(value = "contract:view")
    @RequestMapping("/getNumber")
    public ResultObject getNumber(){
        Integer number = sysContractInfoService.getNumber();
        return new ResultObject(number);
    }

    /**
     * 添加回款数据
     * @param sysContract
     * @return
     */
    @MethodDesc(value = "添加回款数据",type = MethodDesc.Type.ADD)
    @RequiresPermissions(value = "contract:add")
    @RequestMapping("/add")
    public ResultObject addContract(SysContract sysContract){

        sysContractInfoService.addContract(sysContract);
        /**
         * 一期
         */
        Integer period = 1;
        //获取当前回款列表的最大回款编号，写入的回款编号为最大编号加1
        Integer number = sysContractInfoService.findMaxNo()+1;
        sysContractInfoService.addPeriod(number,sysContract.getNumber(),
                sysContract.getIniPaymoney(),
//              写入第一期还款时间，不包含时分秒的格式
                new java.sql.Date(sysContract.getPaytimeOne().getTime()),period);
        /**
         * 二期
         */
        if(null!=sysContract.getSecond()){
            period = 2;
            number +=number;
            sysContractInfoService.addPeriod(number,sysContract.getNumber(),
                    sysContract.getSecPaymoney(),
                    new java.sql.Date(sysContract.getPaytimeTwo().getTime()),period);
        }
        if(sysContract.getThird()!=null){
        /**
         * 三期
         */
            period = 3;
            number +=number;
            sysContractInfoService.addPeriod(number,sysContract.getNumber(),
                    sysContract.getThirdPaymoney(),
                    new java.sql.Date(sysContract.getPaytimeThree().getTime()),period);
        }

        return new ResultObject();
    }

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @MethodDesc(value = "文件上传",type = MethodDesc.Type.OTHER)
    @RequestMapping("/upload")
    public HashMap<String, Object> seUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()){
        String originalFilename = file.getOriginalFilename();
        String filePath = fileRootPath;
        String rootUrl = url;
        File file2 = new File(filePath+originalFilename);
            if(!file2.getParentFile().exists()){
            file2.getParentFile().mkdir();
        }
            HashMap<String, Object> res = new HashMap<>();
            res.put("path",filePath+originalFilename);
            res.put("url",rootUrl+originalFilename);
            System.out.println(filePath+originalFilename);
            System.out.println(rootUrl+originalFilename);
            file.transferTo(file2);

           return res;
    }else {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("fail","失败");
            return stringObjectHashMap;
        }

    }

    /**
     * 查看合同
     * @param id
     * @return
     */
    @MethodDesc(value = "查看合同",type = MethodDesc.Type.VIEW)
    @RequiresPermissions(value = "contract:view")
    @RequestMapping("/info")
    public ResultObject getInfo( Long id) {
        SysContract sysContract = sysContractService.getContractInfo(id);
        ResultObject resultObject = new ResultObject();
        resultObject.setData(sysContract);
        return resultObject;
    }
}
