package com.test;

import com.test.model.User;
import com.test.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

/**
 * Hello world!
 *https://jingyan.baidu.com/article/3065b3b643e773becff8a40c.html
 */
@SpringBootApplication
@EnableCaching
public class App{

    @Bean
    public KeyGenerator keyGenerator(){
       return new KeyGenerator() {
           @Override
           public Object generate(Object target, Method method, Object... objects) {
               StringBuilder sb = new StringBuilder();
               sb.append(target.getClass().getName());
               sb.append(method.getName());
               for(Object obj:objects){
                   if(obj!=null){
                       sb.append(obj.toString());
                   }
               }
               return sb.toString();
           }
       };
    }

    public static void main( String[] args ){
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(App.class,args);
        UserService userService = configurableApplicationContext.getBean(UserService.class);
        User user = userService.findByName("1");
        System.out.println(user);
        User user1 = userService.findByName("1");
        System.out.println(user1);
    }
}
