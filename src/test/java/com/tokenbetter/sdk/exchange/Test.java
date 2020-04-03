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
                .apiKey("....")
                .secretKey("...")
                .passphrase("123456")
                .baseUrl("")
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



        System.out.println(JSON.toJSONString(publicService.fills("BTC_USDT",null)));

    }
}
