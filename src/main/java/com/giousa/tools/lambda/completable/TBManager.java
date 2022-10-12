package com.giousa.tools.lambda.completable;

public class TBManager {

    public static int getPrice() {
        try {
            Thread.sleep(1050);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 150;
    }

    public static int getDiscounts() {
        try {
            Thread.sleep(1050);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 80;
    }
}
