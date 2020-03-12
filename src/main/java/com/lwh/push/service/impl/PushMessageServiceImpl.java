package com.lwh.push.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwh.push.service.PushMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushMessageServiceImpl implements PushMessageService {

    @Value("${getPushMessageUrl}")
    private String getPushMessageUrl;

    @Value("${updatePushMessageUrl}")
    private String updatePushMessageUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public JSONObject getPushMessage() {
        JSONObject result = JSON.parseObject(restTemplate.getForObject(getPushMessageUrl, String.class));
        if (result.getIntValue("code") == 200) {
            return result.getJSONObject("data");
        }
        return null;
    }

    @Override
    public void updatePushMessageStatus(String id, int status) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("status", status);
        restTemplate.postForObject(updatePushMessageUrl, param, String.class);
    }
}
