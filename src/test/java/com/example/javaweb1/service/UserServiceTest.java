package com.example.javaweb1.service;

import com.example.javaweb1.entity.UserEntity;
import com.example.javaweb1.mapper.UserMapper;
import com.example.javaweb1.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void register(){
        try {
            UserEntity user=new UserEntity();
            user.setUsername("susu15");
            user.setPassword("Su202109");
            userService.register(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            throw new RuntimeException(e.getMessage());
        }
    }
    @Test
    public void login(){
        UserEntity user = userService.login("susu9","Su202109");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(20,"susu8","Su202109","Su123456");
    }

    @Test
    public void getById(){
        System.err.println(userService.getById(21));
    }
    @Test
    public void changeInfo(){
        UserEntity user=new UserEntity();
        user.setEmail("2223334444@qq.com");
        user.setSex(1);
        user.setAge(19);
        user.setBirthday("2023/08/20");
        user.setMotto("冲二");
        userService.changeInfo(21,"susu9",user);
    }

    @Test
    public void  changeAvatar(){
        userService.changeAvatar(23,"/img/paopaobing9");
    }

}
