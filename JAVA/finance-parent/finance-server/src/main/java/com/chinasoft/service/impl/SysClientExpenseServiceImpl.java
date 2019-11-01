package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysClientExpenseMapper;
import com.chinasoft.mybatis.dao.SysExpenseMapper;
import com.chinasoft.mybatis.dao.SysExpenseWayMapper;
import com.chinasoft.mybatis.entity.*;
import com.chinasoft.service.SysClientExpenseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.naming.Name;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class SysClientExpenseServiceImpl extends BaseServiceImpl<SysClientExpense> implements SysClientExpenseService {

    @Autowired
    private SysClientExpenseMapper sysClientExpenseMapper;

    @Autowired
    private SysExpenseMapper sysExpenseMapper;

    @Autowired
    private SysExpenseWayMapper sysExpenseWayMapper;

    /**
     * 分页查询客户支出
     * @param pageNum
     * @param pageSize
     * @param sysClientExpense
     * @return
     */
    @Override
    public PageInfo findPage(Integer pageNum, Integer pageSize,SysClientExpense sysClientExpense) {
        PageHelper.startPage(pageNum,pageSize);

        Example example = new Example(SysClientExpense.class);
        Example.Criteria criteria = example.createCriteria();
        //模糊查询条件
        if(!StringUtils.isEmpty(sysClientExpense.getDetails())){
            criteria.orLike("clientName","%"+sysClientExpense.getDetails()+"%");
        }
        //类型筛选条件
        if(null!=sysClientExpense.getType()){
            Example.Criteria addCriteria = example.createCriteria();
            addCriteria.andEqualTo("type",sysClientExpense.getType());
            example.and(addCriteria);
        }
        //时间区间筛选条件
        if((sysClientExpense.getTime()!=null)&&(sysClientExpense.getTimeSearch()!=null)){
            Example.Criteria addCriteria = example.createCriteria();
            Example.Criteria time = addCriteria.andBetween("time", sysClientExpense.getTime(), sysClientExpense.getTimeSearch());
            example.and(addCriteria);
        }
        example.orderBy("id").desc();
        return new PageInfo(sysClientExpenseMapper.selectByExample(example));
    }

    /**
     * 新增客户支出并返回新增ID
     * @param sysClientExpense
     * @return
     */
    @Override
    public int insertClientEx(SysClientExpense sysClientExpense) {
       return sysClientExpenseMapper.insertClientEx(sysClientExpense);
    }

    /**
     * 新增客户的支出途径
     * @param sysExpensee
     * @return
     */
    @Override
    public int insertExpense(SysExpense sysExpensee) {
        return sysExpenseMapper.insert(sysExpensee);
    }

    /**
     * 查询出所有客户名
     * @return
     */
    @Override
    public List<String> findClientName() {
        return sysClientExpenseMapper.findClientName();
    }

    /**
     * 根据ID获取当前客户支出项
     * @param id
     * @return
     */
    @Override
    public SysClientExpense get(@Param("id") Long id) {
        return sysClientExpenseMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据客户ID查询出对应所有支出途径
     * @param clientId
     * @return
     */
    @Override
    public List<SysExpense> getExpense(Long clientId) {
        return sysClientExpenseMapper.selectByClientId(clientId);
    }

    /**
     * 修改当前客户支出项
     * @param sysClientExpense
     */
    @Override
    public void update(SysClientExpense sysClientExpense) {
        sysClientExpenseMapper.updateByPrimaryKeySelective(sysClientExpense);
    }

    /**
     * 查询客户支出途径
     * @return
     */
    @Override
    public List<SysClientExpense> selectSysClientExpense() {
        return sysClientExpenseMapper.selectSysClientExpense();
    }

    /**
     * 新增支持途径
     * @param sysExpenseWay
     * @return
     */
    @Override
    public int insertExpense(SysExpenseWay sysExpenseWay) {
        return sysExpenseWayMapper.insert(sysExpenseWay);
    }

    /**
     * 查询客户支出总金额
     * @return
     */
    @Override
    public Integer seceltClientSum() {
        return sysClientExpenseMapper.seceltClientSum();
    }


}
