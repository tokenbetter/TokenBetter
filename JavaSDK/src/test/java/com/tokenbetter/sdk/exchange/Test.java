package com.tokenbetter.sdk.exchange;

import com.tokenbetter.sdk.client.PrivateApiClient;
import com.tokenbetter.sdk.common.domain.ClientParameter;
import com.tokenbetter.sdk.exchange.service.AccountService;
import com.tokenbetter.sdk.exchange.service.OrderService;
import com.tokenbetter.sdk.exchange.service.PublicService;
import com.alibaba.fastjson.JSON;

public class Test {
    public static void main(String args[]) throws Exception {
        ClientParameter parameter = ClientParameter.builder()
                .apiKey("8c25b7******f958d591")
                .secretKey("7270********be04eb13a3e5bd9f1f3")
                .passphrase("1*****6")
                .baseUrl("https://www.*****.com")
                .build();

        AccountService accountService = PrivateApiClient.builder()
                .configuration(parameter)
                .build().exchange().biz().account();

        OrderService orderService = PrivateApiClient.builder()
                .configuration(parameter)
                .build().exchange().biz().order();

        PublicService publicService = PrivateApiClient.builder()
                .configuration(parameter)
                .build().exchange().biz().pub();



        System.out.println(JSON.toJSONString(accountService.assetsList()));

    }
}
