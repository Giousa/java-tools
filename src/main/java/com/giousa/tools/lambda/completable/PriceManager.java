package com.giousa.tools.lambda.completable;

import java.util.UUID;

public class PriceManager {

    public static PriceResult computeRealPrice(int total, int discounts) {
        System.out.println("PriceManager computeRealPrice. total: " + total + ",discounts: " + discounts+"，thread: "+Thread.currentThread().getName());
        return computeRealPrice(total, discounts, UUID.randomUUID().toString());
    }

    public static PriceResult computeRealPrice(int total, int discounts, String productName) {
        PriceResult priceResult = new PriceResult();
        priceResult.setRealPrice(total - discounts);
        priceResult.setProductName(productName);
        return priceResult;
    }
}
