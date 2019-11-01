package com.chinasoft.vo.output;

/**
 * @Auther: 汪毅
 * @Date: 2018/5/25 10:38
 * @Description: layui 数据表格专用数据结构
 */
public class PageResult {

    private Integer resultCode = 0;
    private String resultMsg = "加载成功";
    private Long count; // 总条数
    private Object data ; // 数据集合

    public PageResult(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public PageResult(Integer code, String msg, Long count, Object data) {
        this.resultCode = code;
        this.resultMsg = msg;
        this.count = count;
        this.data = data;
    }

    public PageResult() {
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
