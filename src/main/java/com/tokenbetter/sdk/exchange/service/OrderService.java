package com.tokenbetter.sdk.exchange.service;

import com.tokenbetter.sdk.exchange.domain.OrdersParam;
import com.tokenbetter.sdk.exchange.domain.OrdersDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Cran
 * @date 2020/01/03
 */
public interface OrderService {
    /**
     * 下单 POST
     */
    Long postOrder(final String code, final OrdersParam param) throws IOException;

    /**
     * 撤单
     */
    void cancelOrders(String code, final Long id) throws IOException;

    /**
     * 撤单
     */
    void cancelOrders(String code, final List<Long> ids) throws IOException;

    /**
     * 挂单列表
     */
    List<OrdersDTO> orderList(final String pairCode, final Long startDate, final Long endDate,
                              final BigDecimal price, final BigDecimal amount, final Integer systemOrderType,
                              final String source, final Integer page, final Integer pageSize) throws IOException;

    /**
     * 完成订单列表
     */
    List<OrdersDTO> orderFinish(final String pairCode,
                                final Long startDate,
                                final Long endDate,
                                final Integer systemOrderType,
                                final BigDecimal price,
                                final BigDecimal amount,
                                final String source,
                                final String isHistory,
                                final Integer page,
                                final Integer pageSize) throws IOException;
    /**
     * 完成订单列表
     */
    Map<String,List<Long>> cancelByIds(final String pairCode,
                                       final List<Long> ids) throws IOException;


    /**
     * 完成订单列表
     */
    OrdersDTO orderById(final String pairCode,final Long id) throws IOException;
}
