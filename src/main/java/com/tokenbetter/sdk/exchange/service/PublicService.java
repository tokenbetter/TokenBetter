package com.tokenbetter.sdk.exchange.service;

import com.tokenbetter.sdk.common.domain.ServerTimeDTO;
import com.tokenbetter.sdk.exchange.domain.CurrencyPairDTO;
import com.tokenbetter.sdk.exchange.domain.OrderBookDTO;
import com.tokenbetter.sdk.exchange.domain.TickerDTO;

import java.io.IOException;
import java.util.List;

public interface PublicService {
    ServerTimeDTO getTime() throws IOException;

    /**
     * 获取币对列表
     */
    List<CurrencyPairDTO> currencies() throws IOException;

    /**
     * 获取k线列表
     */
    List<Object[]> candles(final String pairCode, final String interval,
                           final String start, final String end) throws IOException;


    /**
     * 获取行情数据
     */
    TickerDTO ticker(final String pairCode) throws IOException;

    /**
     * 获取深度数据
     */
    OrderBookDTO orderBook(final String pairCode, final Integer size) throws IOException;

    /**
     * 最新成交列表
     */
    List<Object[]> fills(final String pairCode, final Integer size) throws IOException;
}
