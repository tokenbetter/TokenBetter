package com.tokenbetter.sdk.exchange.domain;

import com.tokenbetter.sdk.common.domain.ParamPageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillResult implements Serializable {
    private List<BillDTO> bills;
    private ParamPageDTO paginate;
}
