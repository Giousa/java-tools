package com.giousa.tools.lambda;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class LambdaTest01 {

    public static void main(String[] args) {
//        test01();
        test02();
    }

    private static void test02() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d");
        Integer index = null;
        String name = null;
        for (Integer it : integers) {
            if (it < 4) {
                continue;
            }
            for (String x : list) {
                if (!Objects.equals(x, "c")) {
                    continue;
                }
                index = it;
                name = x;
                System.out.println("index = " + index + ",name = " + name);
                break;
            }

            if(Objects.nonNull(index) && StringUtils.isNotBlank(name)){
                break;
            }
        }

        System.out.println("下一步： index = " + index + ",name = " + name);
        System.out.println("下次流程");
    }

    private static void test01() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d");

        AtomicReference<Integer> index = null;
        AtomicReference<String> name = null;
        integers.stream().forEach(it -> {
            if (it < 4) {
                return;
            }

            list.stream().forEach(x -> {
                if (!Objects.equals(x, "c")) {
                    return;
                }

//                index.set(it);
//                name.set(x);

                System.out.println("index = " + index + ",name = " + name);

            });
            System.out.println("index = " + it);
        });
    }
}
