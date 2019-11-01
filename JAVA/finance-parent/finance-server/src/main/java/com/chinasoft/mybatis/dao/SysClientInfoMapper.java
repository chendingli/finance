package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysClientInfo;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysClientInfoMapper extends MyMapper<SysClientInfo> {

    List<SysClientInfo> selectClientInfo(@Param("clientInfo") SysClientInfo clientInfo);



}
