package com.giousa.tools.lambda.completable;

public class PDDManager {

    public static int getPrice() {
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 240;
    }

    public static int getDiscounts() {
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 150;
    }
}
