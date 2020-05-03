package com.tokenbetter.sdk.exchange;

import com.tokenbetter.sdk.client.ApiClient;
import com.tokenbetter.sdk.exchange.service.AccountService;
import com.tokenbetter.sdk.exchange.service.OrderService;
import com.tokenbetter.sdk.exchange.service.PublicService;
import com.tokenbetter.sdk.exchange.service.impl.AccountServiceImpl;
import com.tokenbetter.sdk.exchange.service.impl.OrderServiceImpl;
import com.tokenbetter.sdk.exchange.service.impl.PublicServiceImpl;

/**
 * @author Cran
 * @date 2020-01-03
 */
public class ExchangeApiFacade {
    private final ApiClient apiClient;

    public ExchangeApiFacade(final ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * REST API Endpoint
     *
     * @return BizEndpoint
     */
    public BizEndpoint biz() {
        return new BizEndpoint(this.apiClient);
    }

    public static class BizEndpoint {
        private final ApiClient apiClient;

        public BizEndpoint(final ApiClient apiClient) {
            this.apiClient = apiClient;
        }

        /**
         * 创建账户服务
         *
         * @return AccountService
         * @see AccountService
         */
        public AccountService account() {
            return new AccountServiceImpl(this.apiClient);
        }

        /**
         * 创建订单服务
         *
         * @return AccountService
         * @see AccountService
         */
        public OrderService order() {
            return new OrderServiceImpl(this.apiClient);
        }

        /**
         * @return
         */
        public PublicService pub() {
            return new PublicServiceImpl(this.apiClient);
        }
    }
}
