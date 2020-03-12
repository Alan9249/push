package com.lwh.push.service;

import com.alibaba.fastjson.JSONObject;

public interface PushMessageService {

      JSONObject getPushMessage();

      void updatePushMessageStatus(String id, int status);
}
