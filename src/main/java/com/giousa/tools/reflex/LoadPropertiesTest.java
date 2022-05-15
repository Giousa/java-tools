package com.giousa.tools.reflex;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class LoadPropertiesTest {

    public static void main(String[] args) throws Exception {

        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
        List<String> keys = propertiesUtils.getKeys();
        System.out.println("keys: " + JSON.toJSONString(keys));

        String studentClass = propertiesUtils.getValue("student.class");
        String studentMethod = propertiesUtils.getValue("student.method.show");
        String studentParam = propertiesUtils.getValue("student.show.param");
        System.out.println("studentClass: " + studentClass);
        System.out.println("studentMethod: " + studentMethod);
        System.out.println("studentParam: " + studentParam);

        Class clazz = Class.forName(studentClass);
        System.out.println("类：" + clazz);

        Method declaredMethod = clazz.getDeclaredMethod("show", String.class);
        declaredMethod.setAccessible(true);

        Constructor constructor = clazz.getConstructor();
        Object o = constructor.newInstance();

        Object msg = declaredMethod.invoke(o, "配置文件的消息");
        System.out.println("打印消息：" + msg);

        Class[] classes = showClass(studentParam);
        System.out.println("classes : " + JSON.toJSONString(classes));
    }

    public static Class[] showClass(String param) {
        if (StringUtils.isBlank(param)) {
            return null;
        }

        List<Class> list = Lists.newArrayList();
        String[] split = param.split(",");
        System.out.println("split = " + JSON.toJSONString(split));
        for (String s : split) {
            switch (s) {
                case "String":
                    list.add(String.class);
                    break;
                case "int":
                    list.add(Integer.class);
                    break;
                default:
                    list.add(Object.class);
                    break;
            }
        }
        System.out.println("添加后list： " + JSON.toJSONString(list));
        return list.toArray(new Class[list.size()]);
    }
}
