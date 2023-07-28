package com.giousa.spring;

import com.alibaba.fastjson.JSON;
import com.giousa.spring.annotation.Autowired;
import com.giousa.spring.annotation.Component;
import com.giousa.spring.annotation.ComponentScan;
import com.giousa.spring.annotation.Scope;
import com.giousa.spring.bean.BeanDefinition;
import com.giousa.spring.service.BeanPostProcessor;
import com.giousa.spring.service.InitializingBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomApplicationContext {

    private Class configClass;

    /**
     * 会将所有加了Component注解对象，都存储到map容器
     */
    private Map<String, BeanDefinition> beanDefinitionMap = Maps.newHashMap();

    /**
     * 单例池
     */
    private Map<String, Object> singletonObjects = Maps.newHashMap();

    /**
     * 后置处理器容器
     */
    private List<BeanPostProcessor> beanPostProcessorList = Lists.newArrayList();


    public CustomApplicationContext(Class configClass) {

        this.configClass = configClass;
        //扫描
        scan(configClass);

        //单例对象，优先初始化
        initSingletonObjects();
    }


    /**
     * 扫描
     *
     * @param configClass
     */
    private void scan(Class configClass) {
        if (!configClass.isAnnotationPresent(ComponentScan.class)) {
            System.out.println("此配置文件，缺少ComponentScan注解");
            return;
        }

        //获取ComponentScan注解的对象
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);

        //ComponentScan扫描路径
        String path = annotation.value();
        System.out.println("ComponentScan配置的扫描路径：" + path);

        path = path.replace(".", "/");

        //扫描---Component注解的类---生成bean对象
//        URL resource = CustomApplicationContext.class.getClassLoader().getResource("com/giousa/spring");
        URL resource = CustomApplicationContext.class.getClassLoader().getResource(path);
        if (Objects.isNull(resource)) {
            System.out.println("资源文件获取失败！");
            return;
        }
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        if (Objects.isNull(files) || files.length == 0) {
            System.out.println("扫描文件夹数量为空");
            return;
        }

        for (File f : files) {
            System.out.println("扫描的文件 名称：" + f.getName());
            System.out.println("扫描的文件 绝对路径：" + f.getAbsolutePath());
//            Class clazz = CustomApplicationContext.class.getClassLoader().loadClass("com.giousa.spring.service.UserService");

            String fileName = f.getAbsolutePath();
            if (fileName.endsWith(".class")) {
                String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                //com/giousa/spring/UserService
                System.out.println("扫描的文件 类路径：" + className);
                className = className.replace("/", ".");
                System.out.println("className = "+className);
                try {
                    Class clazz = CustomApplicationContext.class.getClassLoader().loadClass(className);
                    if (clazz.isAnnotationPresent(Component.class)) {


                        //判断类是否实现了BeanPostProcessor接口
                        if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                            BeanPostProcessor beanPostProcessor = (BeanPostProcessor) clazz.newInstance();
                            beanPostProcessorList.add(beanPostProcessor);
                        }


                        System.out.println("当前类 包含Component注解： className = " + className);
                        Component component = (Component) clazz.getAnnotation(Component.class);
                        System.out.println("注解beanName： " + component.value());
                        if(StringUtils.isBlank(component.value())){
                            System.out.println("注解beanName是空的："+component.value());
                        }
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(clazz);

                        if (clazz.isAnnotationPresent(Scope.class)) {
                            Scope scope = (Scope) clazz.getAnnotation(Scope.class);
                            beanDefinition.setScope(scope.value());
                        } else {
                            beanDefinition.setScope("singleton");
                        }
                        beanDefinitionMap.put(component.value(), beanDefinition);
                        System.out.println("向对象池，进行注入新对象. beanName: " + component.value());
                        System.out.println("集合："+JSON.toJSONString(beanDefinitionMap));
                    } else {
                        System.out.println("当前类 不包含Component注解： className = " + className);
                    }
                } catch (Exception e) {
                    System.out.println("解析类失败！ className = " + className);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化单例池
     */
    private void initSingletonObjects() {
        if (beanDefinitionMap.size() == 0) {
            System.out.println("对象为空,无法初始化单例池");
            return;
        }

        beanDefinitionMap.forEach((k, v) -> {
            if (!Objects.equals(v.getScope(), "singleton")) {
                return;
            }
            //创建bean
            Object bean = createBean(k, v);
            //放入单例池
            singletonObjects.put(k, bean);
        });

    }

    /**
     * 创建bean
     *
     * @param beanDefinition
     * @return
     */
    private Object createBean(String beanName, BeanDefinition beanDefinition) {

        //实例化对象
        Class clazz = beanDefinition.getClazz();
        Object instance = null;
        try {
            instance = clazz.newInstance();

            //依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    //获取成员变量的名称，进而获取对应的bean
                    Object bean = getBean(field.getName());

                    Autowired autowired = field.getAnnotation(Autowired.class);
                    if (bean == null && autowired.required()) {
                        System.out.println("依赖注入失败！！！ 名称： " + field.getName());
                    } else {
                        //访问控制检查
                        field.setAccessible(true);
                        //赋值
                        field.set(instance, bean);
                    }
                }
            }

            //AOP-初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                //这个处理前，可以替换对象
                Object beforeObj = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
                instance = beforeObj;
            }


            //设置配置完成，初始化一些内部对象
            if (instance instanceof InitializingBean) {
                try {
                    ((InitializingBean) instance).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //AOP-初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                Object afterObj = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
                instance = afterObj;
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Object getBean(String beanName) {
        try {
            if (!beanDefinitionMap.containsKey(beanName)) {
                throw new Exception("类名：" + beanName + " 有误，获取实例失败。beanDefinitionMap = " + JSON.toJSONString(beanDefinitionMap));
            }

            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (Objects.equals(beanDefinition.getScope(), "singleton")) {
                //单例
                System.out.println(beanName + " 开始获取单例");
                return singletonObjects.get(beanName);
            } else {
                //多例
                System.out.println(beanName + " 开始获取多例");
                return createBean(beanName, beanDefinition);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
