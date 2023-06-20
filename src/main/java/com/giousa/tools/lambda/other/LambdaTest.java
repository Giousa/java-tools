package com.giousa.tools.lambda.other;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {


    public static void main(String[] args) {

        // 数组工具Arrays的asList方法可以把一系列元素直接赋值给清单对象
        List<Apple> appleList = Arrays.asList(
                new Apple("红苹果", "RED", 150d, 10d),
                new Apple("大苹果", "green", 250d, 18d),
                new Apple("红苹果", "red", 300d, 29d),
                new Apple("大苹果", "yellow", 200d, 30d),
                new Apple("红苹果", "green", 100d, 34d),
                new Apple("大苹果", "Red", 250d, 56d));


//        List<Apple> apples = PredicateUtils.filterByPredicate(appleList, new Predicate<Apple>() {
//            @Override
//            public boolean test(Apple apple) {
//                return apple.getWeight() > 150;
//            }
//        });

//        List<Apple> apples1 = LambdaUtils.filterByPredicate(appleList, apple -> apple.getWeight() > 150);
//        System.out.println("apples1 >>>> "+apples1);

//        LambdaUtils.modifyByConsumer(appleList,c -> c.setName("前缀："+c.getName()));
//        LambdaUtils.handlerConsumer(appleList,c -> c.setName("前缀: "+c.getName()));

        LambdaUtils.recycleByFunctionV2(appleList,list -> {
            if(appleList.size() > 5){
                return false;
            }
            return true;
        });

        System.out.println("appleList >>>> "+appleList);

    }


}
