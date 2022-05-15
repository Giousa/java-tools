package com.giousa.tools.reflex;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.List;

/**
 * List集合反射，忽略集合泛型
 */
public class ListReflexTest {

    public static void main(String[] args) throws Exception {
        List<String> list = Lists.newArrayList("不笑猫", "博丽灵梦");
        System.out.println("原始数据：" + JSON.toJSONString(list));

        Class clazz = list.getClass();
        Method add = clazz.getMethod("add", Object.class);

        add.invoke(list, 12);
        add.invoke(list, Lists.newArrayList(1, 2, 3));
        add.invoke(list, "hello");

        System.out.println("反射后数据：" + JSON.toJSONString(list));

    }
}
