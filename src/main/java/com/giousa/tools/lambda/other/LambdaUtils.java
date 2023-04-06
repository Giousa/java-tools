package com.giousa.tools.lambda.other;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class LambdaUtils {

    /**
     * 断言接口只进行逻辑判断，不涉及到数据修改
     */
    public static <T> List<T> filterByPredicate(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if (p.test(t)) {
                // 如果满足断言的测试条件，则把该元素添加到新的清单
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 断言接口只进行逻辑判断，不涉及到数据修改，若要修改清单里的元素，就用到了另一个消费接口Consumer。
     */
    public static <T> void modifyByConsumer(List<T> list, Consumer<T> c) {
        for (T t : list) {
            System.out.println("t-> 经过了Consumer转化处理");
            // 根据输入的消费指令接受变更，所谓消费，通俗地说，就是女人花钱打扮自己。
            // 下面的t既是输入参数，又允许修改。
            c.accept(t); // 如果t是String类型，那么accept方法不能真正修改字符串
        }
    }

    // 联合运用Predicate和Consumer，可筛选出某些元素并给它们整容
    public static <T> void selectAndModify(List<T> list, Predicate<T> p, Consumer<T> c) {
        for (T t : list) {
            if (p.test(t)) { // 如果满足断言的条件要求，
                c.accept(t); // 就把该元素送去美容院整容。
            }
        }
    }

    public static void handlerConsumer(List<Apple> list, Consumer<Apple> c) {
        for (Apple t : list) {
            if(t.getPrice() > 20){
                System.out.println("t-> 经过了Consumer转化处理");
                c.accept(t);
            }
        }
    }

    /**
     * 刚才联合断言接口和消费接口，顺利实现了修改部分元素的功能，然而这种做法存在问题，就是直接在原清单上面进行修改
     * 一方面破坏了原始数据，另一方面仍未抽取到新清单，于是Java又设计了泛型的函数接口Function
     */
    public static <T, R> List<R> recycleByFunction(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<R>();
        for (T t : list) {
            R r = f.apply(t); // 把原始材料t加工一番后输出成品r
            result.add(r); // 把成品r添加到新的清单
        }
        return result;
    }
}
