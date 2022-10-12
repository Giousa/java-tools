package com.giousa.tools.lambda.completable;

public class ProductManager {

    public static int getPrice(String productName) {
        try {
            Thread.sleep(1000);
            int length = productName.length();
            if (length == 1) {
                return 10;
            }

            if (length == 2) {
                return 20;
            }

            if (length == 3) {
                return 30;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 100;
    }

    public static int getDiscounts(String productName) {
        try {
            Thread.sleep(1000);
            int length = productName.length();
            if (length == 1) {
                return 1;
            }

            if (length == 2) {
                return 2;
            }

            if (length == 3) {
                return 3;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 10;
    }
}
