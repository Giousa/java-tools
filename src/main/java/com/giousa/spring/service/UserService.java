package com.giousa.spring.service;

import com.giousa.spring.annotation.Autowired;
import com.giousa.spring.annotation.Component;
import com.giousa.spring.annotation.Scope;
import com.giousa.spring.bean.User;
import org.apache.commons.lang3.RandomUtils;

import java.util.UUID;

/**
 * UserService--->BeanDefinition--->Map<BeanName,BeanDefinition>
 */
@Component("userService")
//@Scope("prototype")
@Scope("singleton")
public class UserService implements InitializingBean{

    @Autowired
    private OrderService orderService;

    private User defaultUser;

    public void printfOrder() {
        System.out.println("orderService = " + orderService);
    }

    /**
     * 配置设置完毕后执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //mysql --> user
        defaultUser = new User();
        defaultUser.setName(UUID.randomUUID().toString());
        defaultUser.setAge(RandomUtils.nextInt(10,20));
    }

    public User getDefaultUser() {
        return defaultUser;
    }
}

