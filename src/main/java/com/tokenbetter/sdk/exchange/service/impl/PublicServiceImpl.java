package com.tokenbetter.sdk.exchange.service.impl;

import com.tokenbetter.sdk.client.ApiClient;
import com.tokenbetter.sdk.common.domain.ServerTimeDTO;
import com.tokenbetter.sdk.exchange.domain.CurrencyPairDTO;
import com.tokenbetter.sdk.exchange.domain.OrderBookDTO;
import com.tokenbetter.sdk.exchange.service.PublicService;
import com.tokenbetter.sdk.exchange.api.PublicApi;
import com.tokenbetter.sdk.exchange.domain.TickerDTO;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class PublicServiceImpl implements PublicService {
    private final PublicApi publicApi;

    public PublicServiceImpl(final ApiClient client) {
        this.publicApi = client.create(PublicApi.class);
    }

    @Override
    public ServerTimeDTO getTime() throws IOException {
        final Call<ServerTimeDTO> call = this.publicApi.getTime();
        return call.execute().body();
    }

    @Override
    public List<CurrencyPairDTO> currencies() throws IOException {
        final Call<List<CurrencyPairDTO>> call = this.publicApi.currencies();
        return call.execute().body();
    }

    @Override
    public List<Object[]> candles(String pairCode, String interval, String start, String end) throws IOException {
        final Call<List<Object[]>> call = this.publicApi.candles(pairCode, interval, start, end);
        return call.execute().body();
    }

    @Override
    public TickerDTO ticker(String pairCode) throws IOException {
        final Call<TickerDTO> call = this.publicApi.ticker(pairCode);
        return call.execute().body();
    }

    @Override
    public OrderBookDTO orderBook(String pairCode, Integer size) throws IOException {
        final Call<OrderBookDTO> call = this.publicApi.orderBook(pairCode, size);
        return call.execute().body();
    }

    @Override
    public List<Object[]> fills(String pairCode, Integer size) throws IOException {
        final Call<List<Object[]>> call = this.publicApi.fills(pairCode, size);
        return call.execute().body();
    }
}
