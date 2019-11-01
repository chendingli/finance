package com.chinasoft.exception;

/**
 * @Description:业务异常
 * @Author Joe zhangzezhou123@gmail.com
 * @Date 2017/11/6 10:22
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}