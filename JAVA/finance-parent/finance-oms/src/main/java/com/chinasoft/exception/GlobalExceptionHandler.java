package com.chinasoft.exception;

import com.chinasoft.util.code.StatusCode;
import com.chinasoft.vo.output.ResultObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Object handleAuthenticationException(UnauthorizedException e) {
        logger.error(ExceptionUtils.getStackTrace(e));  // 记录错误信息
        String msg = "没有权限,禁止访问";
        ResultObject resultObject = ResultObject.ResultObjectBuilder.aResultObject()
                .withResultCode(StatusCode.STATUS_USER_OPERATE_UNAUTHORIZED)
                .withResultMsg(msg)
                .withErrorMsg(e.getMessage())
                .build();
        return resultObject;
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Object handleBusinessException(Exception e) {
        logger.error(ExceptionUtils.getStackTrace(e));  // 记录错误信息
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "业务异常";
        }
        ResultObject resultObject = ResultObject.ResultObjectBuilder.aResultObject()
                .withResultCode(StatusCode.STATUS_BUSINESS_ERROR)
                .withResultMsg(msg)
                .withErrorMsg(e.getMessage())
                .build();
        return resultObject;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        logger.error(ExceptionUtils.getStackTrace(e));  // 记录错误信息
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        ResultObject resultObject = ResultObject.ResultObjectBuilder.aResultObject()
                .withResultCode(StatusCode.STATUS_ERROR)
                .withResultMsg(msg)
                .withErrorMsg(ExceptionUtils.getStackTrace(e))
                .build();
        return resultObject;
    }

}
