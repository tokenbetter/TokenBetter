package com.tokenbetter.sdk.exchange.api;

import com.tokenbetter.sdk.exchange.domain.Assets;
import com.tokenbetter.sdk.exchange.domain.BillResult;
import com.alibaba.fastjson.JSONArray;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author Cran
 * @date 2020/01/03
 */
public interface AccountApi {
    @GET("/openapi/exchange/assets")
    Call<List<Assets>> assetsList();

    @GET("/openapi/exchange/{symbol}/assets")
    Call<Assets> assets(@Path("symbol") String symbol);

    @GET("/openapi/exchange/bills")
    Call<BillResult> billList(@Query("page") final Integer page,
                              @Query("pageSize") final Integer pageSize,
                              @Query("startDate") final Long startDate,
                              @Query("endDate") final Long endDate,
                              @Query("symbol") final String symbol,
                              @Query("type") final Integer type,
                              @Query("isHistory") final Boolean isHistory);

    @GET("/openapi/exchange/billTypes")
    Call<JSONArray> billTypeList();
}
