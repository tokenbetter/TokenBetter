package com.tokenbetter.sdk.exchange.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Cran
 * @date 2020-01-03 14:23:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TickerDTO {

    private String high;

    private String low;

    private String sell;

    private String change24;

    private String changeRate24;

    private String high24;

    private String low24;

    private String volume;

    private String quoteVolume;

    private String last;

    private String buy;

    private String pairCode;

    private String changePercentage;

    private String open;

    private String close;

    private Long createOn;
}