package com.tokenbetter.sdk.exchange.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cran
 * @date 2020/01/03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersDTO {

    private Long id;

    private String pairCode;

    private Long userId;

    private Integer brokerId;

    private String side;

    private String entrustPrice;

    private String amount;

    private String dealAmount;

    private String quoteAmount;

    private String dealQuoteAmount;

    private Integer systemOrderType;

    private Integer status;

    private String sourceInfo;

    private Long createOn;

    private Long updateOn;

    private String symbol;

    private String trunOver;

    private String notStrike;

    private String averagePrice;

    private String openAmount;
}
