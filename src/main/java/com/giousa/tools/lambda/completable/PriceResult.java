package com.giousa.tools.lambda.completable;

import lombok.Data;

@Data
public class PriceResult {

    /**
     * 真实价格
     */
    private int realPrice;

    /**
     * 产品名称
     */
    private String productName;
}
