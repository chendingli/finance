package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysPersonl;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
public interface SysPersonlService extends BaseService<SysPersonl> {

    //序列自动填充+1
    Integer getUnmberId();
    //添加员工列表
    PageInfo<SysPersonl> findPage(Integer pageNum, Integer pageSize,SysPersonl sysPersonl);

    //得到序号
    SysPersonl get(Long id);

    void insertPersonl(SysPersonl sysPersonl);
    //表格导出
    void downExcle(HttpServletResponse response,String str) throws IOException;
    void exportExcel(HttpServletResponse response, String title, Class<?> pojoClass, Collection<?> dataSet) throws IOException;


}
