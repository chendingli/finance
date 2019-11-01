package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysContractInfo;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 陈定立
 * @Date: 2018/12/14
 * @Description: 合同Dao层
 */

@Mapper
public interface SysContractInfoMapper extends MyMapper<SysContractInfo> {

    /**
     * 初始化合同列表
     * @param sysContractInfo ：参数注解，sql的模糊查询
     * @return
     */
    List<SysContractInfo> selectContractInfo(@Param("sysContractInfo") SysContractInfo sysContractInfo);

    /**
     * 得到合同表最大合同编号
     * @return
     */
    Integer getNumber();

    /**
     * 往回款表写数据
     * @param number 回款编号
     * @param contractNo 合同编号
     * @param backMoney 还款额
     * @param estimateTime 预计还款时间
     * @param period 还款期数
     */
    void addPeriod(@Param("number")Integer number,
                   @Param("contractNo")Integer contractNo,
                   @Param("backMoney")Double backMoney,
                   @Param("estimateTime")Date estimateTime,
                   @Param("period")Integer period);

    /**
     * 查询回款表最大回款编号
     * @return
     */
    Integer findMaxNo();

   List<SysContractInfo> selectSumContract(SysContractInfo sysContractInfo);
}
