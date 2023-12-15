package com.example.javaweb1.mapper;

import com.example.javaweb1.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        UserEntity user=new UserEntity();
        user.setUsername("susu11");
        user.setPassword("Su202109");
        int rows=userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void finByUsername(){
        UserEntity user=userMapper.findByUsername("susu");
        System.out.println(user);
    }

    @Test
    public void updatePasswordById(){
        userMapper.updatePasswordById(Integer.parseInt("16"),"Su123456");

    }
    @Test
    public void findById(){
        System.out.println(userMapper.findById(Integer.parseInt("20")));

    }
    @Test
    public void updateInfoById(){
        UserEntity user = new UserEntity();
        user.setId(18);
        user.setNickName("炮炮兵PING");
        user.setSex(0);
        user.setBirthday("2022/12/08");
        user.setAge(Integer.valueOf("17"));
        user.setEmail("1234567899@qq.com");
        user.setMotto("qianqian");
        userMapper.updateInfoById(user);
    }

    @Test
    public void updateAvatarById(){
        userMapper.updateAvatarById(23,"/img/paopaobing3.jpg");
    }
}
