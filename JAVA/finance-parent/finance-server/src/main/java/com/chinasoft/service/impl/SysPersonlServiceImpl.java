package com.chinasoft.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.chinasoft.mybatis.dao.SysPersonlMapper;
import com.chinasoft.mybatis.entity.*;
import com.chinasoft.service.SysPersonlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
@Service
@Transactional
public class SysPersonlServiceImpl extends BaseServiceImpl<SysPersonl> implements SysPersonlService {

    @Autowired
    private SysPersonlMapper sysPersonlMapper;

    @Override
    public PageInfo<SysPersonl> findPage(Integer pageNum, Integer pageSize,SysPersonl sysPersonl) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(SysPersonl.class);
        Example.Criteria criteria = example.createCriteria();

        //根据姓名、员工工号查询员工
        if(!StringUtils.isEmpty(sysPersonl.getName())){
            criteria.orLike("name","%"+sysPersonl.getName()+"%");
            criteria.orLike("unmberId","%"+sysPersonl.getName()+"%");
        }
        //根据员工合同类型查询员工
        if(!StringUtils.isEmpty(sysPersonl.getType())){
            Example.Criteria addCriteria = example.createCriteria();
            addCriteria.andEqualTo("type",sysPersonl.getType());
            example.and(addCriteria);
        }
        //根据时间段查询员工
        if((sysPersonl.getRtime()!=null)&&(sysPersonl.getTimeSearch()!=null)){
                Example.Criteria addCriteria = example.createCriteria();
                addCriteria.andBetween("rtime", sysPersonl.getRtime(),sysPersonl.getTimeSearch());
                example.and(addCriteria);

        }
        //根据员工在职状态查询员工
        if(!StringUtils.isEmpty(sysPersonl.getState())){
        }if(!"".equals(sysPersonl.getState())){
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.andEqualTo("state",sysPersonl.getState());
            example.and(criteria1);
        }
        //添加员工姓名倒序
        example.orderBy("id").desc();
        PageInfo<SysPersonl> pageInfo = new PageInfo(sysPersonlMapper.selectByExample(example));

        return pageInfo;

    }

    @Override
    public SysPersonl get(Long id) {

        SysPersonl sysPersonl = sysPersonlMapper.selectByPrimaryKey(id);

        return sysPersonl;
    }

    // 保存角色
    @Override
    public void insertPersonl(SysPersonl sysPersonl) {

        sysPersonlMapper.insertPersonl(sysPersonl);

    }

    //表格导出
    public void downExcle(HttpServletResponse response,String str) throws IOException{

        List<SysPersonl> list = getAllSys();

        System.out.println(list.toString());

        exportExcel(response,"员工列表",SysPersonl.class,list);

    }

    //表格导出
    public void exportExcel(HttpServletResponse response,String title,Class<?> pojoClass,Collection<?> dataSet)throws IOException{
        Workbook book = null;
        ServletOutputStream outStream=null;
        try {
            response.setHeader("Content-type", "application/octet-stream");
            response.setHeader("content-disposition",
                    String.format("attachment;filename*=utf-8'zh_cn'%s.xls", URLEncoder.encode(title, "utf-8")));
            outStream = response.getOutputStream();

             book = ExcelExportUtil.exportExcel(new ExportParams(title, title, ExcelType.HSSF), pojoClass, dataSet);
            book.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            book.close();
            outStream.close();
        }
    }

    //序号自动填充并+1方法
    public List<SysPersonl> getAllSys() {

        return sysPersonlMapper.selectAll();
    }
    public Integer getUnmberId() {
        return sysPersonlMapper.getUnmberId();
    }
}