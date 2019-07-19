package com.test.service.impl;

import com.test.model.User;
import com.test.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    @Cacheable(cacheNames = {"usersq"},keyGenerator = "keyGenerator")
    public User findByName(String name) {
        System.out.println("==========UserServiceImpl执行了,name=" + name);
        if ("1".equals(name)) {
            return new User("1", "hello");
        } else {
            return new User("2", "world");

        }
    }
}
