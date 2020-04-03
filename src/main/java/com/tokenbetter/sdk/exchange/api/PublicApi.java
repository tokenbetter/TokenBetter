package com.tokenbetter.sdk.exchange.api;

import com.tokenbetter.sdk.common.domain.ServerTimeDTO;
import com.tokenbetter.sdk.exchange.domain.CurrencyPairDTO;
import com.tokenbetter.sdk.exchange.domain.OrderBookDTO;
import com.tokenbetter.sdk.exchange.domain.TickerDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author Cran
 * @date 2020/01/03
 */
public interface PublicApi {
    /**
     * 获取时间
     */
    @GET("/openapi/exchange/public/time")
    Call<ServerTimeDTO> getTime();

    /**
     * 获取币对列表
     */
    @GET("/openapi/exchange/public/currencies")
    Call<List<CurrencyPairDTO>> currencies();

    /**
     * 获取k线列表
     */
    @GET("/openapi/exchange/public/{pairCode}/candles")
    Call<List<Object[]>> candles(@Path("pairCode") final String pairCode,
                                 @Query("interval") final String interval,
                                 @Query("start") final String start,
                                 @Query("end") final String end);


    /**
     * 获取行情数据
     */
    @GET("/openapi/exchange/public/{pairCode}/ticker")
    Call<TickerDTO> ticker(@Path("pairCode") final String pairCode);

    /**
     * 获取深度数据
     */
    @GET("/openapi/exchange/public/{pairCode}/orderBook")
    Call<OrderBookDTO> orderBook(@Path("pairCode") final String pairCode,
                                 @Query("size") final Integer size);

    /**
     * 最新成交列表
     */
    @GET("/openapi/exchange/public/{pairCode}/fills")
    Call<List<Object[]>> fills(@Path("pairCode") final String pairCode,
                               @Query("size") final Integer size);


}
