package com.lwh.push.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lwh.push.service.PushMessageService;
import com.lwh.push.util.AndroidPushUtil;
import com.lwh.push.util.IOSPushUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.LinkedList;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@Slf4j
public class PushMessageScheduler {

    @Autowired
    PushMessageService pushMessageService;

    @Autowired
    AndroidPushUtil androidPushUtil;

    @Autowired
    IOSPushUtil iosPushUtil;

    @Scheduled(cron = "0/5 * * * * ?")
    public void pushMessage() {
        JSONObject pushMessage = pushMessageService.getPushMessage();
        if (pushMessage != null) {
            log.info("message id:{} push start...", pushMessage.getString("id"));
            String message = pushMessage.getString("message");
            String title = pushMessage.getString("title");
            String num = pushMessage.getString("number");
            List<String> androidIdentifies = new LinkedList<>();
            List<String> iosIdentifies = new LinkedList<>();
            JSONArray phoneIdentifies = pushMessage.getJSONArray("phoneIdentifies");
            for (JSONObject phoneIdentify : phoneIdentifies.toJavaList(JSONObject.class)) {
                if (phoneIdentify.getIntValue("phoneOs") == 1) {
                    androidIdentifies.add(phoneIdentify.getString("identify"));
                } else {
                    iosIdentifies.add(phoneIdentify.getString("identify"));
                }
            }
            if (androidIdentifies.size() > 0) {
                androidPushUtil.pushMessage(androidIdentifies, message, title, num);
            }
            if (iosIdentifies.size() > 0) {
                iosPushUtil.pushMessage(iosIdentifies, message,title, num);
            }
            pushMessageService.updatePushMessageStatus(pushMessage.getString("id"), 2);
            log.info("message id:{} push end...", pushMessage.getString("id"));
        } else {
            log.info("no message");
        }
    }
}
