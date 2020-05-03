package com.tokenbetter.sdk.exchange.service.impl;

import com.tokenbetter.sdk.client.ApiClient;
import com.tokenbetter.sdk.exchange.domain.Assets;
import com.tokenbetter.sdk.exchange.domain.BillResult;
import com.tokenbetter.sdk.exchange.service.AccountService;
import com.tokenbetter.sdk.exchange.api.AccountApi;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * @author Cran
 * @date 2020/01/03
 */
public class AccountServiceImpl implements AccountService {
    private final AccountApi accountApi;

    public AccountServiceImpl(final ApiClient client) {
        this.accountApi = client.create(AccountApi.class);
    }

    @Override
    public List<Assets> assetsList() throws IOException {
        final Call<List<Assets>> call = this.accountApi.assetsList();
        return call.execute().body();
    }

    @Override
    public Assets assets(final String symbol) throws IOException {
        final Call<Assets> call = this.accountApi.assets(symbol);
        return call.execute().body();
    }

    @Override
    public BillResult billList(Integer page, Integer pageSize, Long startDate,
                               Long endDate, String symbol, Integer type, Boolean isHistory) throws IOException {
        final Call<BillResult> call = this.accountApi.billList(page, pageSize,
                startDate, endDate, symbol, type, isHistory);
        return call.execute().body();
    }
}
