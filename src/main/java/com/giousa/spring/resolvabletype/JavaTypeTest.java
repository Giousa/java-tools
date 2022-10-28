package com.giousa.spring.resolvabletype;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Type 表示类型，java 所有的原生类型、参数化类型、变量类型、原子类型等都是该类实现。其主要实现有：
 * java.lang.Class 类
 * java.lang.reflect.ParameterizedType  参数化类型
 * java.lang.reflect.WildcardType WildcardType represents a wildcard type expression, such as {@code ?}, {@code ? extends Number}, or {@code ? super Integer}
 * java.lang.reflect.TypeVariable  List<T> 这样的类型
 */
public class JavaTypeTest {

    public static void main(String[] args) throws Exception {
        System.out.println("JavaTypeTest start......");
        // 类
        Type[] genericInterfaces = WrapperTest.class.getGenericInterfaces();
        System.out.println("类genericInterfaces = " + genericInterfaces);

        // 属性
        Field field = WrapperTest.class.getDeclaredField("ids");
        System.out.println("属性ids field = " + field);
        Type genericType = field.getGenericType();
        System.out.println("属性ids genericType = " + genericType);
        Field field2 = WrapperTest.class.getDeclaredField("names");
        Type genericType2 = field2.getGenericType();
        Field field3 = WrapperTest.class.getDeclaredField("names2");
        Type genericType3 = field3.getGenericType();

        // 方法
        Method method = JavaTypeTest.class.getMethod("test01", List.class, Map.class, String.class, WrapperTest.class, String[].class, int[].class);
        // 获取方法参数
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        System.out.println("方法参数genericParameterTypes = "+genericParameterTypes+", 参数个数："+genericParameterTypes.length);
        // 获取返回值
        Type genericReturnType = method.getGenericReturnType();
        System.out.println("返回值genericReturnType = " + genericReturnType);
        // 如果想获取实际类型，ParameterizedType 表示是一种参数化类型，也就是泛型. 返回实际类型的数组， 比如Map<String, Object> 就是两个Type
        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        System.out.println("返回值的实际类型actualTypeArguments[0] = " + actualTypeArguments[0]); // actualTypeArguments[0] 实际是个class 对象
        // 获取其实际类型
        Type rawType = ((ParameterizedType) genericReturnType).getRawType(); // List.class, rawType实际是个class 对象
        System.out.println("返回值的rawType = " + rawType);
//        Object invoke = method.invoke(null,null,null,null,null,null);
//        method.setAccessible(true);
//        List<String> listResult = (List<String>) method.invoke(null,null,null,null,null,null);
//        System.out.println("反射返回结果listResult = "+listResult);
    }

    public static List<String> test01(List<Integer> datas, Map<String, Object> map, String s, WrapperTest wrapper, String[] strs, int[] ints) throws NoSuchMethodException {
        System.out.println("执行了test01方法");
        List<String> list = new ArrayList<>();
        list.add("这个是测试");
        list.add("你好呀");
        return list;
    }

}