package com.chinasoft.util.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.chinasoft.util.common.JsonStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Map;

public class Jpush {
    protected static final Logger logger = LoggerFactory.getLogger(Jpush.class);

    private String appKey;

    private String masterSecret;

    private JPushClient jpushClient;

    // 定义当前是否是生产环境，从而决定iOS通知的发送方式
    private String iosNotificationProdFlag;

    private String sendValidFlag;

    public static final String MODE_ALL = "mode_all";
    public static final String MODE_ALAS = "mode_alas";
    public static final String MODE_TAG = "mode_tag";


    @PostConstruct
    public void init() {
        try {
            if (jpushClient == null) {
                jpushClient = new JPushClient(this.masterSecret, this.appKey);
                logger.info("logger: 极光推送：初始化成功");
            }
        } catch (Exception e) {
            logger.error("极光推送：初始化异常", e);
        }
    }

    // 推送安卓信息
    // Alert格式：通知文本
    // Content格式：Json字符串，{"Type":"消息主题","Content":"通知文本"}
    // Identifier格式：用户ID
    public boolean pushToAndroidNotification(String title, String alert, Map<String, String> message, String mode, String identifier) {
        logger.info("极光（Android）推送标示位" + sendValidFlag);
        logger.info("极光 (Android)推送,标题:" + title + ",警示:" + alert + ",内容:" + JsonStringUtil.parseToJson(message) + ",目标:" + identifier);
        if (!"true".equals(sendValidFlag)) {
            return true;
        }
        PushResult result = null;
        PushPayload payload = null;

        switch (mode) {
            case Jpush.MODE_ALAS:
                payload = buildAndroidAliasNotification(title, alert, message, identifier);
                break;
            case Jpush.MODE_TAG:
                payload = buildAndroidTagNotification(title, alert, message, identifier);
                break;
            case Jpush.MODE_ALL:
                payload = buildAndroidAllNotification(title, alert, message);
                break;
            default:
                payload = buildAndroidAllNotification(title, alert, message);
                break;
        }

        long startTime = System.currentTimeMillis();
        logger.info("极光（Android）推送开始");
        try {
            if (this.jpushClient != null) {
                result = this.jpushClient.sendPush(payload);
            }
            long endTime = System.currentTimeMillis();
            logger.info("极光（Android）发送时间为：" + (endTime - startTime));
            logger.info("极光（Android）发送：成功发送，结果是 [" + result + "]");

        } catch (APIConnectionException e) {
            logger.error("极光（Android）推送：连接失败，请稍后再试", e);
            return false;

        } catch (APIRequestException e) {
            logger.error("极光（Android）推送：接收错误的服务器响应，请检查并修正。Status: [" + e.getStatus() + "], " +
                    "Error Code: [" + e.getErrorCode() + "], Error Message: [" + e.getErrorMessage() + "], " +
                    "Msg ID: [" + e.getMsgId() + "]", e);

            return false;

        } catch (Exception e) {
            logger.error("极光（Android）推送异常", e);

            return false;
        }

        return true;
    }

    // 推送iOS信息
    // Alert格式：通知文本
    // Content格式：Json字符串，{"Type":"消息主题","Content":"通知文本"}
    // Identifier格式：用户ID
    public boolean pushToIosNotification(String title, String alert, Map<String, String> msgContent, String mode, String identifier) {
        logger.info("极光（IOS）推送标示位" + sendValidFlag);
        logger.info("极光 (IOS)推送,标题:" + title + ",警示:" + alert + ",内容:" + JsonStringUtil.parseToJson(msgContent) + ",目标:" + identifier);
        if (!"true".equals(sendValidFlag)) {
            return true;
        }
        PushResult result = null;
        PushPayload payload = null;
        switch (mode) {
            case Jpush.MODE_ALAS:
                payload = buildIosAliasNotification(title, alert, msgContent, identifier);
                break;
            case Jpush.MODE_TAG:
                payload = buildIosTagNotification(title, alert, msgContent, identifier);
                break;
            case Jpush.MODE_ALL:
                payload = buildIosAllNotification(title, alert, msgContent);
                break;
            default:
                payload = buildIosAllNotification(title, alert, msgContent);
                break;
        }

        long startTime = System.currentTimeMillis();
        logger.info("极光（iOS）推送开始");

        try {
            if (this.jpushClient != null) {
                result = this.jpushClient.sendPush(payload);
            }
            long endTime = System.currentTimeMillis();
            logger.info("极光（iOS）发送时间为：" + (endTime - startTime));
            logger.info("极光（iOS）发送：成功发送，结果是 [" + result + "]");

        } catch (APIConnectionException e) {
            logger.error("极光（iOS）推送：连接失败，请稍后再试", e);
            return false;

        } catch (APIRequestException e) {
            logger.error("极光（iOS）推送：接收错误的服务器响应，请检查并修正。Status: [" + e.getStatus() + "], " +
                    "Error Code: [" + e.getErrorCode() + "], Error Message: [" + e.getErrorMessage() + "], " +
                    "Msg ID: [" + e.getMsgId() + "]", e);

            return false;

        } catch (Exception e) {
            logger.error("极光（iOS）推送异常", e);

            return false;
        }

        return true;
    }

    // 推送安卓信息
    // Alert格式：通知文本
    // Content格式：Json字符串，{"Type":"消息主题","Content":"通知文本"}
    // Identifier格式：用户ID
    public boolean pushToAndroidCustomMsg(String title, String alert, Map<String, Object> message, String mode, String identifier) {
        logger.info("极光（Android）推送标示位" + sendValidFlag);
        logger.info("极光 (Android)推送,标题:" + title + ",警示:" + alert + ",内容:" + JsonStringUtil.parseToJson(message) + ",目标:" + identifier);
        if (!"true".equals(sendValidFlag)) {
            return true;
        }
        PushResult result = null;
        PushPayload payload = null;

        switch (mode) {
            case Jpush.MODE_ALAS:
                payload = buildAndroidAliasCustomMsg(title, alert, message, identifier);
                break;
            case Jpush.MODE_TAG:
                payload = buildAndroidTagCustomMsg(title, alert, message, identifier);
                break;
            case Jpush.MODE_ALL:
                payload = buildAndroidAllCustomMsg(title, alert, message);
                break;
            default:
                payload = buildAndroidAllCustomMsg(title, alert, message);
                break;
        }

        long startTime = System.currentTimeMillis();
        logger.info("极光（Android）推送开始");
        try {
            if (this.jpushClient != null) {
                result = this.jpushClient.sendPush(payload);
            }
            long endTime = System.currentTimeMillis();
            logger.info("极光（Android）发送时间为：" + (endTime - startTime));
            logger.info("极光（Android）发送：成功发送，结果是 [" + result + "]");

        } catch (APIConnectionException e) {
            logger.error("极光（Android）推送：连接失败，请稍后再试", e);
            return false;

        } catch (APIRequestException e) {
            logger.error("极光（Android）推送：接收错误的服务器响应，请检查并修正。Status: [" + e.getStatus() + "], " +
                    "Error Code: [" + e.getErrorCode() + "], Error Message: [" + e.getErrorMessage() + "], " +
                    "Msg ID: [" + e.getMsgId() + "]", e);

            return false;

        } catch (Exception e) {
            logger.error("极光（Android）推送异常", e);

            return false;
        }

        return true;
    }

    // 推送iOS信息
    // Alert格式：通知文本
    // Content格式：Json字符串，{"Type":"消息主题","Content":"通知文本"}
    // Identifier格式：用户ID
    public boolean pushToIosCustomMsg(String title, String alert, Map<String, String> msgContent, String mode, String identifier) {
        logger.info("极光（IOS）推送标示位" + sendValidFlag);
        logger.info("极光 (IOS)推送,标题:" + title + ",警示:" + alert + ",内容:" + JsonStringUtil.parseToJson(msgContent) + ",目标:" + identifier);
        if (!"true".equals(sendValidFlag)) {
            return true;
        }
        PushResult result = null;
        PushPayload payload = null;
        switch (mode) {
            case Jpush.MODE_ALAS:
                payload = buildIosAliasCustomMsg(title, alert, msgContent, identifier);
                break;
            case Jpush.MODE_TAG:
                payload = buildIosTagCustomMsg(title, alert, msgContent, identifier);
                break;
            case Jpush.MODE_ALL:
                payload = buildIosAllCustomMsg(title, alert, msgContent);
                break;
            default:
                payload = buildIosAllCustomMsg(title, alert, msgContent);
                break;
        }

        long startTime = System.currentTimeMillis();
        logger.info("极光（iOS）推送开始");

        try {
            if (this.jpushClient != null) {
                result = this.jpushClient.sendPush(payload);
            }
            long endTime = System.currentTimeMillis();
            logger.info("极光（iOS）发送时间为：" + (endTime - startTime));
            logger.info("极光（iOS）发送：成功发送，结果是 [" + result + "]");

        } catch (APIConnectionException e) {
            logger.error("极光（iOS）推送：连接失败，请稍后再试", e);
            return false;

        } catch (APIRequestException e) {
            logger.error("极光（iOS）推送：接收错误的服务器响应，请检查并修正。Status: [" + e.getStatus() + "], " +
                    "Error Code: [" + e.getErrorCode() + "], Error Message: [" + e.getErrorMessage() + "], " +
                    "Msg ID: [" + e.getMsgId() + "]", e);

            return false;

        } catch (Exception e) {
            logger.error("极光（iOS）推送异常", e);

            return false;
        }

        return true;
    }

    // android: 别名
    protected PushPayload buildAndroidAliasNotification(String title,
                                                        String alert,
                                                        Map<String, String> msgContent,
                                                        String alias) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(alert, title, msgContent))
                .build();
    }

    // android: 组播
    protected PushPayload buildAndroidTagNotification(String title,
                                                      String alert,
                                                      Map<String, String> msgContent,
                                                      String tag) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag_and(tag))
                .setNotification(Notification.android(alert, title, msgContent))
                .build();
    }

    // android: 广播
    protected PushPayload buildAndroidAllNotification(String title,
                                                      String alert,
                                                      Map<String, String> msgContent) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(alert, title, msgContent))
                .build();
    }

    // ios: 别名
    protected PushPayload buildIosAliasNotification(String title,
                                                    String alert,
                                                    Map<String, String> msgContent,
                                                    String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
//								.setBadge(5)
                                .autoBadge()
                                .setSound("default")
                                .addExtras(msgContent)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(iosNotificationProdFlag))                // 生产环境需要设为true
                        .build())
                .build();
    }

    // ios: 组播
    protected PushPayload buildIosTagNotification(String title, String alert, Map<String, String> msgContent, String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tag))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
//								.setBadge(5)
                                .autoBadge()
                                .setSound("default")
                                .addExtra("from", "common_framework")
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(iosNotificationProdFlag))                // 生产环境需要设为true
                        .build())
                .build();
    }

    // ios: 广播
    protected PushPayload buildIosAllNotification(String title, String alert, Map<String, String> msgContent) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
//								.setBadge(5)
                                .autoBadge()
                                .setSound("default")
                                .addExtras(msgContent)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(iosNotificationProdFlag))                // 生产环境需要设为true
                        .build())
                .build();
    }

    // android: 别名
    protected PushPayload buildAndroidAliasCustomMsg(String title,
                                                     String alert,
                                                     Map<String, Object> msgContent,
                                                     String alias) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
//				.setMessage(Message.newBuilder()
//						.setMsgContent("自定义消息内容")
//						.setTitle(title)
//						.addExtra("message extras key", "message extras value")
//						.build())
                .setMessage(Message.content(JsonStringUtil.parseToJson(msgContent)))
                .build();
    }

    // android: 组播
    protected PushPayload buildAndroidTagCustomMsg(String title,
                                                   String alert,
                                                   Map<String, Object> msgContent,
                                                   String tag) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag_and(tag))
//				.setMessage(Message.newBuilder()
//						.setMsgContent("自定义消息内容")
//						.setTitle(title)
//						.addExtra("message extras key", "message extras value")
//						.build())
                .setMessage(Message.content(JsonStringUtil.parseToJson(msgContent)))
                .build();
    }

    // android: 广播
    protected PushPayload buildAndroidAllCustomMsg(String title,
                                                   String alert,
                                                   Map<String, Object> msgContent) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
//			.setMessage(Message.newBuilder()
//						.setMsgContent("自定义消息内容")
//						.setTitle(title)
//						.addExtra("message extras key", "message extras value")
//						.build())
                .setMessage(Message.content(JsonStringUtil.parseToJson(msgContent)))
                .build();
    }

    // ios: 别名
    protected PushPayload buildIosAliasCustomMsg(String title,
                                                 String alert,
                                                 Map<String, String> msgContent,
                                                 String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(alias))
//				.setMessage(Message.newBuilder()
//						.setMsgContent("自定义消息内容")
//						.setTitle(title)
//						.addExtra("message extras key", "message extras value")
//						.build())
                .setMessage(Message.content(JsonStringUtil.parseToJson(msgContent)))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(iosNotificationProdFlag))                // 生产环境需要设为true
                        .build())
                .build();
    }

    // ios: 组播
    protected PushPayload buildIosTagCustomMsg(String title, String alert, Map<String, String> msgContent, String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tag))
//				.setMessage(Message.newBuilder()
//						.setMsgContent("自定义消息内容")
//						.setTitle(title)
//						.addExtra("message extras key", "message extras value")
//						.build())
                .setMessage(Message.content(JsonStringUtil.parseToJson(msgContent)))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(iosNotificationProdFlag))                // 生产环境需要设为true
                        .build())
                .build();
    }

    // ios: 广播
    protected PushPayload buildIosAllCustomMsg(String title, String alert, Map<String, String> msgContent) {

        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
//				.setMessage(Message.newBuilder()
//						.setMsgContent("自定义消息内容")
//						.setTitle(title)
//						.addExtra("message extras key", "message extras value")
//						.build())
                .setMessage(Message.content(JsonStringUtil.parseToJson(msgContent)))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(Boolean.valueOf(iosNotificationProdFlag))                // 生产环境需要设为true
                        .build())
                .build();
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public void setIosNotificationProdFlag(String iosNotificationProdFlag) {
        this.iosNotificationProdFlag = iosNotificationProdFlag;
    }

    public void setSendValidFlag(String sendValidFlag) {
        this.sendValidFlag = sendValidFlag;
    }
}
