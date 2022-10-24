package com.giousa.tools.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class LambdaTest01 {

    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }


    private static void sendMsg(Long id, String name, String user, Function<FunctionUser, FunctionUser> function) {

        if (Objects.isNull(user) || StringUtils.isBlank(user)) {
            System.out.println("user不能为空");
            return;
        }

        FunctionUser functionUser = function.apply(JSON.parseObject(user, FunctionUser.class));
        if (Objects.isNull(functionUser)) {
            System.out.println("数据解析失败");
            return;
        }

        System.out.println("解析后数据：" + JSON.toJSONString(functionUser));
    }

    private static void test03() {
        Long id = 11L;
        String name = "不笑猫";
        FunctionUser user = new FunctionUser();
        user.setUsername("测试用户");
        user.setAge(12);
        user.setUrl("http://giousa.com/getUserById?id={id}&name={name}");

        System.out.println("解析前数据：" + JSON.toJSONString(user));

        sendMsg(id, name, JSON.toJSONString(user), functionUser -> {
            if (StringUtils.isBlank(functionUser.getUrl())) {
                return null;
            }

            functionUser.setUrl(StringUtils.replaceEach(functionUser.getUrl(), new String[]{"{id}", "{name}"}, new String[]{Objects.toString(id), name}));

            return functionUser;
        });
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

            if (Objects.nonNull(index) && StringUtils.isNotBlank(name)) {
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
