package com.chinasoft.task;

import com.chinasoft.mybatis.entity.SysLog;
import com.chinasoft.service.SysLogService;
import com.chinasoft.util.common.SpringUtils;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/9 16:08
 * @Description:
 */
public class InsertLogTask implements Runnable{

    private SysLogService sysLogService = SpringUtils.getBean(SysLogService.class);

    private SysLog sysLog;

    public InsertLogTask (SysLog sysLog) {
        this.sysLog = sysLog;
    }

    /**
     * 异步保存日志
     */
    @Override
    public void run() {
        sysLogService.insertSelective(sysLog);
    }

}
