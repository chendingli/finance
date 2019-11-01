package com.chinasoft.service.impl;

import cn.hutool.core.util.StrUtil;
import com.chinasoft.mybatis.dao.SysLogMapper;
import com.chinasoft.mybatis.entity.SysLog;
import com.chinasoft.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/9 15:57
 * @Description:
 */
@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLog get(Long id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<SysLog> findPage(Integer pageNum, Integer pageSize, SysLog log) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(SysLog.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(log.getTitle())) {
            criteria.andLike("title", "%" + log.getTitle() + "%");
        }
        if (StrUtil.isNotBlank(log.getCreateBy())) {
            criteria.andLike("createBy", "%"+log.getCreateBy()+"%");
        }
        if (null != log.getType()) {
            criteria.andEqualTo("type", log.getType());
        }
        if (null != log.getBeginDate()) {
            criteria.andGreaterThanOrEqualTo("createTime", log.getBeginDate());
        }
        if (null != log.getEndDate()) {
            criteria.andLessThanOrEqualTo("createTime", log.getEndDate());
        }

        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogMapper.selectByExample(example));
        return pageInfo;
    }
}
