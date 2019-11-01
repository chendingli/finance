package com.chinasoft.util.common;

/**
 * @Auther: 汪毅
 * @Date: 2018/7/4 16:12
 * @Description:
 */
public class TemplateUtil {

    /**
     * 交易成功通知
     *
     * @param openid
     * @param url
     * @param first
     * @param type
     * @param shop
     * @param amount
     * @param detail
     * @param remark
     * @return
     */
    public static String tradeSuccess(String openid, String url, String first, String type, String shop, String amount, String detail, String remark) {
        String templateId = "l8h-mbaOuLEO9HPREhMo198X6zBqMc7MLq2FTOxjVBE";
        String msg = "{\n" +
                "           \"touser\":\"" + openid + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"" + url + "\",  \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + first + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + type + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"" + shop + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\"" + amount + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\": {\n" +
                "                       \"value\":\"" + detail + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"" + remark + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        return msg;
    }

    /**
     * 退款成功通知
     *
     * @param openid
     * @param url
     * @param first
     * @param refundAmount
     * @param detail
     * @param orderNum
     * @param remark
     * @return
     */
    public static String refundSuccess(String openid, String url, String first, String refundAmount, String detail, String orderNum, String remark) {
        String templateId = "ajxSFPWpfa2GY0CS9OHOE1jZOJ7q6E8NADG-nLdO1qU";
        String msg = "{\n" +
                "           \"touser\":\"" + openid + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"" + url + "\",  \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + first + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"orderProductPrice\":{\n" +
                "                       \"value\":\"" + refundAmount + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"orderProductName\": {\n" +
                "                       \"value\":\"" + detail + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"orderName\": {\n" +
                "                       \"value\":\"" + orderNum + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"" + remark + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        return msg;
    }

    /**
     * 退款驳回通知
     *
     * @param openid
     * @param url
     * @param first
     * @param refuseDesc
     * @param refuseTime
     * @param remark
     * @return
     */
    public static String refuseRefund(String openid, String url, String first, String refuseDesc, String refuseTime, String remark) {
        String templateId = "_ENAn6M4n1heV49oVXdUinBqzZMBEU8TrabRs54C9sU";
        String msg = "{\n" +
                "           \"touser\":\"" + openid + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"" + url + "\",  \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + first + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + refuseDesc + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"" + refuseTime + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"" + remark + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(TemplateUtil.tradeSuccess("1234", "http://baidu.com", "恭喜您，支付成功", "套餐支付", "天安云谷店", "20.56元", "2小时套餐", "欢迎下次体验"));
    }
}
