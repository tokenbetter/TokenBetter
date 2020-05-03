package com.tokenbetter.sdk.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
}
