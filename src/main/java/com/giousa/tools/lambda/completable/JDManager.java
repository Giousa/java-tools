package com.giousa.tools.lambda.completable;

public class JDManager {

    public static int getPrice() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 100;
    }

    public static int getDiscounts() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 20;
    }
}
