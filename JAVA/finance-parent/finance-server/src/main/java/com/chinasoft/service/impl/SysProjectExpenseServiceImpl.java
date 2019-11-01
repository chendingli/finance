package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysProjectExpenseMapper;
import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysProjectExpense;
import com.chinasoft.service.SysProjectExpenseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class SysProjectExpenseServiceImpl extends BaseServiceImpl<SysProjectExpense> implements SysProjectExpenseService {

    @Autowired
    private SysProjectExpenseMapper sysProjectExpenseMapper;

    /**
     * 分页查询项目支出项
     * @param pageNum
     * @param pageSize
     * @param sysProjectExpense
     * @return
     */
    @Override
    public PageInfo findPage(Integer pageNum, Integer pageSize, SysProjectExpense sysProjectExpense) {
        PageHelper.startPage(pageNum,pageSize);

        Example example = new Example(SysProjectExpense.class);
        Example.Criteria criteria = example.createCriteria();
        //模糊查询条件
        if(!StringUtils.isEmpty(sysProjectExpense.getDetails())){
            criteria.orLike("contractNumber","%"+sysProjectExpense.getDetails()+"%");
            criteria.orLike("projectName","%"+sysProjectExpense.getDetails()+"%");
            criteria.orLike("createName","%"+sysProjectExpense.getDetails()+"%");
        }
        //类别筛选
        if(null!=sysProjectExpense.getType()){
            Example.Criteria addCriteria = example.createCriteria();
            addCriteria.andEqualTo("type",sysProjectExpense.getType());
            example.and(addCriteria);
        }
        //时间区间筛选
        if((sysProjectExpense.getTime()!=null)&&(sysProjectExpense.getTimeSearch()!=null)){
            Example.Criteria addCriteria = example.createCriteria();
            Example.Criteria time = addCriteria.andBetween("time", sysProjectExpense.getTime(), sysProjectExpense.getTimeSearch());
            example.and(addCriteria);
        }

        example.orderBy("id").desc();
        return new PageInfo(sysProjectExpenseMapper.selectByExample(example));
    }

    /**
     * 新增合同支出
     * @param sysProjectExpense
     */
    @Override
    public void insertProjectExpense(SysProjectExpense sysProjectExpense) {

        sysProjectExpenseMapper.insertSelective(sysProjectExpense);
    }

    /**
     * 根据合同编号查找对应合同名
     * @param number
     * @return
     */
    @Override
    public String findContractNameByNumber(Integer number) {
        return sysProjectExpenseMapper.findContractNameByNumber(number);
    }

    /**
     * 查询当前项目支出项
     * @param id
     * @return
     */
    @Override
    public SysProjectExpense get(Long id) {

        SysProjectExpense sysProjectExpense = sysProjectExpenseMapper.selectByPrimaryKey(id);
        return sysProjectExpense;
    }

    /**
     * 修改项目支出项
     * @param sysProjectExpense
     */
    @Override
    public void update(SysProjectExpense sysProjectExpense) {
        sysProjectExpenseMapper.updateByPrimaryKeySelective(sysProjectExpense);
    }

    /**
     * 查询所有合同名称
     * @return
     */
    @Override
    public List<String> findContract() {
        return sysProjectExpenseMapper.findContract();
    }

    /**
     * 根据合同名查询对应编号
     * @param name
     * @return
     */
    @Override
    public Integer findNumberByName(String name) {
        return sysProjectExpenseMapper.findNumberByName(name);
    }

    /**
     * 查询项目支出总金额
     * @return
     */
    @Override
    public Integer selectProject() {
        return sysProjectExpenseMapper.selectProject();
    }

    @Override
    public List<SysProjectExpense> selectProjectExpense() {
        return sysProjectExpenseMapper.selectProjectExpense();
    }


}
