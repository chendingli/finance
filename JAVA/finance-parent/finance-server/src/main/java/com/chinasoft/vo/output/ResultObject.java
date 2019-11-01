package com.chinasoft.vo.output;

import com.chinasoft.util.code.StatusCode;
import com.chinasoft.util.code.StatusCodeUtil;

/**
 * @author Joe zhangzezhou123@gmail.com
 * @description:响应结果
 * @date 2018/5/2 11:45
 */
public class ResultObject {
    /**
     * 响应码，响应码详见com.chinasoft.util.code.StatusCode.java
     */
    private String resultCode = StatusCode.STATUS_SUCCESS;
    /**
     * 响应提示
     */
    private String resultMsg = StatusCodeUtil.getStatusMsg(StatusCode.STATUS_SUCCESS);
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 响应数据
     */
    private Object data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultObject() {
    }

    public ResultObject(String resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public ResultObject(Object data) {
        this.resultCode = StatusCode.STATUS_SUCCESS;
        this.resultMsg = StatusCodeUtil.getStatusMsg(resultCode);
        this.data = data;
    }

    public static final class ResultObjectBuilder {
        private String resultCode = StatusCode.STATUS_SUCCESS;
        private String resultMsg;
        private String errorMsg;
        private Object data;

        private ResultObjectBuilder() {
        }

        public static ResultObjectBuilder aResultObject() {
            return new ResultObjectBuilder();
        }

        public ResultObjectBuilder withResultCode(String resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        public ResultObjectBuilder withResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
            return this;
        }

        public ResultObjectBuilder withErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public ResultObjectBuilder withData(Object data) {
            this.data = data;
            return this;
        }

        public ResultObject build() {
            ResultObject resultObject = new ResultObject();
            resultObject.setResultCode(resultCode);
            resultObject.setResultMsg(resultMsg);
            resultObject.setErrorMsg(errorMsg);
            resultObject.setData(data);
            return resultObject;
        }
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
