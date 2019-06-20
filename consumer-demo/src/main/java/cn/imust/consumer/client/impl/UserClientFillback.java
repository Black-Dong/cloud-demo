package cn.imust.consumer.client.impl;

import cn.imust.consumer.client.UserClient;
import cn.imust.consumer.pojo.User;
import org.springframework.stereotype.Component;


@Component
public class UserClientFillback implements UserClient {
    /**
     * 熔断逻辑
     * @param id
     * @return
     */
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setName("未知用户！！！");
        return user;
    }
}
