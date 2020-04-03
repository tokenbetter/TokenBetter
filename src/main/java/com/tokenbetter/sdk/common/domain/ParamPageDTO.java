package com.tokenbetter.sdk.common.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Cran
 * @date 2020/01/03
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParamPageDTO {

    public static final Integer DEFAULT_PAGE_INDEX = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 300;
    public static final Integer AMOUNT_PAGE_SIZE = 10;

    private Integer page;

    private Integer pageSize;

    private Integer total;

    public ParamPageDTO() {
        if (this.page == null) {
            this.page = DEFAULT_PAGE_INDEX;
        }

        if (this.pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }

    }

    public static ParamPageDTO toParamPage(Integer page, Integer pageSize) {
        if (page == null || page <= 0) {
            page = DEFAULT_PAGE_INDEX;
        }

        if (pageSize == null || pageSize <= 0) {
            pageSize = AMOUNT_PAGE_SIZE;
        }

        final ParamPageDTO paramPageDTO = ParamPageDTO.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        return paramPageDTO;
    }

}
