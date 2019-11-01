package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysAdministrationExpenseMapper;
import com.chinasoft.mybatis.entity.SysAdministrationExpense;
import com.chinasoft.service.SysAdministrationExpenseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import java.util.Collections;
import java.util.List;

import java.util.List;

@Service
@Transactional
public class SysAdministrationExpenseServiceImpl extends BaseServiceImpl<SysAdministrationExpense> implements SysAdministrationExpenseService {

    @Autowired
    private SysAdministrationExpenseMapper sysAdministrationExpenseMapper;

    /**
     * 分页查询行政支出
     * @param pageNum
     * @param pageSize
     * @param sysAdministrationExpense
     * @return
     */
    @Override
    public PageInfo findPage(Integer pageNum, Integer pageSize, SysAdministrationExpense sysAdministrationExpense) {
        PageHelper.startPage(pageNum,pageSize);

        Example example = new Example(SysAdministrationExpense.class);
        Example.Criteria criteria = example.createCriteria();

        //模糊查询条件
        if(!StringUtils.isEmpty(sysAdministrationExpense.getDetails())){
            criteria.orLike("createName","%"+sysAdministrationExpense.getDetails()+"%");
        }
        //类型筛选
        if(null!=sysAdministrationExpense.getType()){
            Example.Criteria addCriteria = example.createCriteria();
            addCriteria.andEqualTo("type",sysAdministrationExpense.getType());
            example.and(addCriteria);
        }
        //时间区间筛选
        if((sysAdministrationExpense.getTime()!=null)&&(sysAdministrationExpense.getTimeSearch()!=null)){
            Example.Criteria addCriteria = example.createCriteria();
            Example.Criteria time = addCriteria.andBetween("time", sysAdministrationExpense.getTime(), sysAdministrationExpense.getTimeSearch());
            example.and(addCriteria);
        }
        example.orderBy("number").desc();

        return new PageInfo(sysAdministrationExpenseMapper.selectByExample(example));
    }

    /**
     * 新增行政支出项
     * @param sysAdministrationExpense
     */
    @Override
    public void insertAdministratinoExpense(SysAdministrationExpense sysAdministrationExpense) {
        sysAdministrationExpenseMapper.insertSelective(sysAdministrationExpense);
    }

    /**
     * 根据ID获取单条行政支出项
     * @param id
     * @return
     */
    @Override
    public SysAdministrationExpense get(Long id) {
        return sysAdministrationExpenseMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改当前行政支出
     * @param sysAdministrationExpense
     */
    @Override
    public void update(SysAdministrationExpense sysAdministrationExpense) {
        sysAdministrationExpenseMapper.updateByPrimaryKeySelective(sysAdministrationExpense);
    }

    /**
     * 查询总支出
     * @return
     */
    @Override
    public Integer selectSumExpense() {
        return sysAdministrationExpenseMapper.selectSumExpense();
    }

    @Override
    public List<SysAdministrationExpense> selectAdministrationExpense() {
        return sysAdministrationExpenseMapper.selectAdministrationExpense();
    }


}
