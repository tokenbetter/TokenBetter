package com.tokenbetter.sdk.exchange.service;

import com.tokenbetter.sdk.exchange.domain.Assets;
import com.tokenbetter.sdk.exchange.domain.BillResult;

import java.io.IOException;
import java.util.List;

/**
 * @author Cran
 * @date 2019/01/03
 */
public interface AccountService {
    /**
     * 获取资产信息
     */
    List<Assets> assetsList() throws IOException;

    /**
     * 获取资产信息
     */
    Assets assets(final String symbol) throws IOException;

    /**
     * 获取账单列表
     */
    BillResult billList(final Integer page, final Integer pageSize, final Long startDate,
                        final Long endDate, final String symbol, final Integer type, final Boolean isHistory) throws IOException;
}
