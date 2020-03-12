package com.lwh.push.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwh.push.service.PushMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @Autowired
    PushMessageService pushMessageService;

    @GetMapping("/test")
    public void test1(){
        pushMessageService.updatePushMessageStatus("3", 2);
    }

    @GetMapping("/test2")
    public void test2(){
        JSONObject result = pushMessageService.getPushMessage();
    }
}
