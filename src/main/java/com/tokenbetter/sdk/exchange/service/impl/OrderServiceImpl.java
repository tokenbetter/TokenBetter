package com.tokenbetter.sdk.exchange.service.impl;


import com.tokenbetter.sdk.client.ApiClient;
import com.tokenbetter.sdk.exchange.domain.OrdersParam;
import com.tokenbetter.sdk.exchange.service.OrderService;
import com.tokenbetter.sdk.exchange.api.OrderApi;
import com.tokenbetter.sdk.exchange.domain.OrdersDTO;
import retrofit2.Call;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Cran
 * @date 2020/01/03
 */
public class OrderServiceImpl implements OrderService {

    private final OrderApi orderApi;

    public OrderServiceImpl(final ApiClient client) {
        this.orderApi = client.create(OrderApi.class);
    }

    @Override
    public Long postOrder(final String code, final OrdersParam param) throws IOException {
        final Call<Long> call = this.orderApi.postOrder(code, param);
        System.out.println(call.execute().message());;

        return call.execute().body();
    }

    @Override
    public void cancelOrders(final String code, final Long id) throws IOException {
        this.orderApi.cancel(code, id).execute();
    }

    @Override
    public void cancelOrders(final String code, final List<Long> ids) throws IOException {
        this.orderApi.cancel(code, ids).execute();
    }

    @Override
    public List<OrdersDTO> orderList(final String pairCode, final Long startDate, final Long endDate,
                                     final BigDecimal price, final BigDecimal amount, final Integer systemOrderType,
                                     final String source, final Integer page, final Integer pageSize) throws IOException {
        final Call<List<OrdersDTO>> call = this.orderApi.orderList(pairCode, startDate,
                endDate, price, amount, systemOrderType, source, page, pageSize);
        return call.execute().body();
    }

    @Override
    public List<OrdersDTO> orderFinish(String pairCode, Long startDate, Long endDate, Integer systemOrderType, BigDecimal price,
                                       BigDecimal amount, String source, String isHistory, Integer page, Integer pageSize) throws IOException {
        final Call<List<OrdersDTO>> call = this.orderApi.orderFinish(pairCode, startDate, endDate,
                systemOrderType, price, amount, source, isHistory, page, pageSize);
        return call.execute().body();
    }

    @Override
    public Map<String, List<Long>> cancelByIds(String pairCode, List<Long> ids) throws IOException {
        final Call<Map<String,List<Long>>> call = this.orderApi.cancelByIds(pairCode, ids);
        return call.execute().body();
    }

    public OrdersDTO orderById(String pairCode, Long id) throws IOException {
        final Call<OrdersDTO> call = this.orderApi.orderById(pairCode,id);
        return call.execute().body();
    }
}
