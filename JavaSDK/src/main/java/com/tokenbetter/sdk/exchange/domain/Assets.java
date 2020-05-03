package com.tokenbetter.sdk.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author wcs
 * @date 2020/01/03
 * @des 用户资产信息
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assets {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 券商id
     */
    private Integer brokerId;
    /**
     * 币种ID
     */
    private String symbol;
    /**
     * 余额
     */
    private BigDecimal available;
    /**
     * 冻结
     */
    private BigDecimal hold;
    /**
     * 提币限额
     */
    private BigDecimal withdrawLimit;
    /**
     * 折合成 BTC 的估值
     */
    private BigDecimal baseBTC;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否可划转 true:划转，false:不可划转
     */
    private Boolean transfer;
}
