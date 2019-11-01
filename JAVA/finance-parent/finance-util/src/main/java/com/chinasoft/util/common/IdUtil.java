package com.chinasoft.util.common;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/27 15:46
 * @Description:
 */
public class IdUtil {

    /**
     * ID生成器 19位 Long 型数据
     * @return
     */
    public static Long genID() {
        return BitConverter.ToInt64(RandomUtil.simpleUUID().getBytes(), 0);
    }

    public static void main(String[] args) {
        JSONObject data = new JSONObject();
        data.put("scene", 1);
        String json = data.toJSONString();
        String encStr = SecureUtil.aes("ABCdef9087654321".getBytes()).encryptBase64(json);
        System.out.println(encStr);
        String resp = HttpRequest.post("http://localhost:8080/client/api/ext/getShowQrcodeUrl").header("Content-type","application/json").send(encStr).body();
        System.out.println(resp);
        //String a ="ITCuQ+pj4hwXbO6EiClg6C+rA4alG45ht2e1qn73bnxvwLeyWjWJ4D0JdNkjMkcY1ySV1oT3jB2CwcuuHTxTXTyxT9+V687nzpSsz8aPRq5qGd70QTtGKqZsZVuhc58KAHHkPf071RW8CYMBlKGCNe8O5GRmA2LoVerDT1t1FP+A4zoxlZRcmaaja2aC8szxCPnkROxNpMCOPy0RvJ17ZQ==";
        String b = SecureUtil.aes("ABCdef9087654321".getBytes()).decryptStrFromBase64(resp);
        System.out.println(b);
    }

}
