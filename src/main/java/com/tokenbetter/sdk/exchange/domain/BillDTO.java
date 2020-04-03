package com.tokenbetter.sdk.exchange.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BillDTO {

    private Long id;

    private Long userId;

    private Integer brokerId;

    private String symbol;

    private Integer type;

    private BigDecimal amount;

    private BigDecimal assets;

    private String makerTaker;

    private BigDecimal fee;

    private Long referId;

    private String tradeNo;

    private Long createOn;

    private Long updateOn;

    private String pairCode;

    private BigDecimal beforeAssets;

    private BigDecimal afterAssets;

    private BigDecimal price;
}