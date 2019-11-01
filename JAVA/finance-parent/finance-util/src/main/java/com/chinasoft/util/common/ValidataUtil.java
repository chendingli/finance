package com.chinasoft.util.common;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 汪毅
 * @Date: 2018/5/5 09:10
 * @Description:
 */
public class ValidataUtil {

    /**
     * 获取错误消息
     * @param result
     * @return 返回JSON格式
     */
    public static String getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return JSON.toJSONString(map);
    }
}
