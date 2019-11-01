package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysPersonl;
import com.chinasoft.util.MyMapper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Repository
public interface SysPersonlMapper extends MyMapper<SysPersonl> {

        void insertPersonl(SysPersonl sysPersonl);

        Integer getUnmberId();

        PageInfo<SysPersonl> findPage(Integer pageNum, Integer pageSize,SysPersonl sysPersonl);
}