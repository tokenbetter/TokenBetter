package com.tokenbetter.sdk.exchange.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 交易市场表
 *
 * @author wcs-team
 * @date 2018-12-19 21:20:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyPairDTO {

    private Long id;

    private String baseSymbol;

    private String quoteSymbol;

    private String pairCode;

    private Integer quotePrecision;

    private BigDecimal minTrade;

    private Integer maxPrice;

    private Integer maxVolume;

    private Integer sort;

    private BigDecimal baseIncrement;

    private BigDecimal quoteIncrement;

    private Integer online;

    private BigDecimal makerFeesRate;

    private BigDecimal tickerFeesRate;

    private BigDecimal lastPrice;
}