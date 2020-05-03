package com.tokenbetter.sdk.exchange.api;

import com.tokenbetter.sdk.exchange.domain.OrdersParam;
import com.tokenbetter.sdk.exchange.domain.OrdersDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Cran
 * @date 2020/01/03
 */
public interface OrderApi {

    /**
     * 下单接口
     */
    @POST("/openapi/exchange/{pairCode}/orders")
    Call<Long> postOrder(@Path("pairCode") String pairCode, @Body OrdersParam body);

    /**
     * 撤单
     */
    @HTTP(method = "DELETE", path = "/openapi/exchange/{pairCode}/orders/{id}", hasBody = true)
    Call<Void> cancel(@Path("pairCode") String pairCode, @Path("id") Long id);

    /**
     * 撤单
     */
    @HTTP(method = "DELETE", path = "/openapi/exchange/{pairCode}/orders", hasBody = true)
    Call<Void> cancel(@Path("pairCode") String pairCode, @Body List<Long> ids);

    /**
     * 挂单列表
     */
    @GET("/openapi/exchange/orders")
    Call<List<OrdersDTO>> orderList(@Query("pairCode") final String pairCode,
                                    @Query("startDate") final Long startDate,
                                    @Query("endDate") final Long endDate,
                                    @Query("price") final BigDecimal price,
                                    @Query("amount") final BigDecimal amount,
                                    @Query("systemOrderType") final Integer systemOrderType,
                                    @Query("source") final String source,
                                    @Query("page") final Integer page,
                                    @Query("pageSize") final Integer pageSize);

    /**
     * 完成订单列表
     */
    @GET("/openapi/exchange/{pairCode}/fulfillment")
    Call<List<OrdersDTO>> orderFinish(@Path("pairCode") final String pairCode,
                                      @Query("startDate") final Long startDate,
                                      @Query("endDate") final Long endDate,
                                      @Query("systemOrderType") final Integer systemOrderType,
                                      @Query("price") final BigDecimal price,
                                      @Query("amount") final BigDecimal amount,
                                      @Query("source") final String source,
                                      @Query("isHistory") final String isHistory,
                                      @Query("page") final Integer page,
                                      @Query("pageSize") final Integer pageSize);



    /**
     * 撤单
     */
    @HTTP(method = "DELETE", path = "/openapi/exchange/{pairCode}/cancelByIds", hasBody = true)
    Call<Map<String,List<Long>>> cancelByIds(@Path("pairCode") String pairCode, @Query("ids") final List<Long> ids);



    /**
     * 根据交易对查询订单
     */

    @HTTP(method = "GET", path = "/openapi/exchange/{pairCode}/orders/{id}")
    Call<OrdersDTO> orderById(@Path("pairCode") String pairCode,@Path("id")Long id);

}
