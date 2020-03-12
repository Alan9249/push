package com.lwh.push.util;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class IOSPushUtil {
    /**
     * APNS推送需要的证书、密码、和设备的Token
     **/
    @Value("${ios.p12}")
    private String p12Path;

    @Value("${ios.password}")
    private String password;

    static String p12Patht = "E:/test.p12";
    static String passwordt = "Password123";
    static String pushToken = "e23eb8c495781896012f6a9671bad00fb1a0d1109e53305d238073d5de0c4556";


    public void pushMessage(List<String> pushTokenList, String message, String title, String num){
        ApnsService service =
                APNS.newService()
                        .withCert(p12Path, password)
//                        .withSandboxDestination()
                        .withProductionDestination()
                        .build();
        String payload = APNS.newPayload().customField("num", num).alertTitle(title).alertBody(message)
                .badge(Integer.parseInt(num)).sound("default").build();
        Collection<? extends ApnsNotification> result = service.push(pushTokenList, payload);
        System.out.println(result);
    }
}

