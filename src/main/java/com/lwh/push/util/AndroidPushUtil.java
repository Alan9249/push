package com.lwh.push.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AndroidPushUtil {

    @Value("${android.appKey}")
    private String APP_KEY;

    @Value("${android.secret}")
    private String MASTER_SECRET;

    private static String appKey = "ab84f70ce029e9c17b864a4d";
    private static String secret = "bf421c747aab701368ca0a95";

    public void pushMessage(List<String> aliases, String message, String title, String num) {
        pushMessage(false, aliases, message, title, num);
    }

    public void pushMessage(boolean isAll, List<String> aliases, String message, String title, String num) {
        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        Map<String, String> extra = new HashMap<>();
        extra.put("num", num);
        PushPayload pushPayload = buildPayload(isAll, aliases, message, title, extra);
        try {
            PushResult pushResult = jpushClient.sendPush(pushPayload);
            log.info("pushResult: {}", pushResult);  // 成功推送后输出id和sendno
        } catch (APIConnectionException e) {
            e.printStackTrace();
            log.error("API error");
        } catch (APIRequestException e) {
            e.printStackTrace();
            log.error("Request error");
        }

    }

    private PushPayload buildPayload(boolean isAll, List<String> alias, String message, String title, Map<String, String> extra) {
        PushPayload.Builder payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setNotification(Notification.android(message, title, extra)) // 发送的内容
                //这里是指定开发环境,不用设置也没关系
//                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setMessage(Message.content(message));
        if (isAll) {
            payload.setAudience(Audience.all());
        } else {
            payload.setAudience(Audience.alias(alias));
        }
        return payload.build();
    }
}

