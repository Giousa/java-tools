package com.giousa.tools.reflex;

import java.io.InputStream;
import java.util.*;

public class PropertiesUtils {

    private static PropertiesUtils propertiesUtils;

    private Properties p;

    /**
     * 私有的构造方法，初始化Properties
     *
     * 1. Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
     *
     * 2. Class.getClassLoader.getResourceAsStream(String path) ：默认则是从ClassPath根下获取，path不能以’/'开头，最终是由ClassLoader获取资源。
     */
    private PropertiesUtils() {
        try {
            p = new Properties();
//            InputStream is = PropertiesUtils.class.getClassLoader().getResourceAsStream("admin.properties");
            InputStream is = PropertiesUtils.class.getResourceAsStream("/admin.properties");
            if (Objects.isNull(is)) {
                throw new Exception("解析资源文件失败");
            }
            p.load(is);
        } catch (Exception e) {
            System.out.println("Properties 初始化失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取实例
     */
    public static synchronized PropertiesUtils getInstance() {
        if (Objects.isNull(propertiesUtils)) {
            propertiesUtils = new PropertiesUtils();
        }
        return propertiesUtils;
    }

    /**
     * 获取配置value
     */
    public String getValue(String key) {
        return p.getProperty(key);
    }

    /**
     * 获取所有key
     *
     * @return
     */
    public List<String> getKeys() {
        Set<String> strings = p.stringPropertyNames();
        if (Objects.isNull(strings)) {
            return null;
        }
        return new ArrayList<>(strings);
    }


}
