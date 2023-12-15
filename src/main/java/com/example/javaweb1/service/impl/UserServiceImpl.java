package com.example.javaweb1.service.impl;

import com.example.javaweb1.entity.UserEntity;
import com.example.javaweb1.mapper.UserMapper;
import com.example.javaweb1.service.UserService;
import com.example.javaweb1.service.exception.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(UserEntity user) {
        /*根据参数user对象获取注册的用户名*/
        String username=user.getUsername();
       /* 调用持久层的UserEntity findByUsername(String username)方法，根据用户名查询用户数据*/
        UserEntity result =userMapper.findByUsername(username);
        if (result != null){
            throw  new UsernameOccupedException("用户名已存在");
        }

        /*给密码加密  盐值+密码+盐值(md5算法）*/
        String oldPassword = user.getPassword();
        /*获取随机生成的盐值*/
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt); //补全盐值数据
        /*将密码和盐值作为一个整体进行加密处理*/
        String md5Password = getMd5Password(oldPassword,salt);
        /*将加密后的密码重新补全设置到user对象中*/
        user.setPassword(md5Password);

        user.setIsDelete(0);  // 补全数据：isDelete(0)

        /*执行注册业务功能的实现（rows==1*/
        int rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("注册过程中发生未知异常");
        }
    }

    @Override
    public UserEntity login(String username, String password) {
        System.out.println("=====================================11");
        System.out.println(username);
        System.out.println(password);
        System.out.println("=====================================11");
        /*调用userMapper的findByUsername()方法，根据参数username查询用户数据*/
        UserEntity result = userMapper.findByUsername(username);
        /*判断查询结果是否为null*/
        if (result == null || result.getIsDelete() == 1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newMd5Password = getMd5Password(password,salt);

        /*判断查询结果中的密码，与以上加密得到的密码是否不一致*/
        if (!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("密码验证失败异常");
        }

        /*创建新的UserEntity对象*/
        UserEntity user = new UserEntity();

        /*将查询结果中的id、username、avatar封装到新的user对象中*/
        user.setId(result.getId());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar()); //返回有用户的头像
        return user;
    }

    @Override
    public void changePassword(int id, String username, String oldPassword, String newPassword) {
        /*调用userMapper的findById()方法，根据参数Id查询用户数据*/
        UserEntity result = userMapper.findById(id);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
       /* 从查询结果中取出盐值
       * 将参数oldPassword结合盐值加密，得到oldMd5Password*/
        String oldMd5Password = getMd5Password(oldPassword,result.getSalt());
       /* 判断查询结果中的password与oldMd5Password是否不一致*/
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
       /* 将参数newPassword结合盐值加密，得到newMd5Password*/
        String newMd5Passwoed = getMd5Password(newPassword,result.getSalt());
        /*调用userMapper的updatePasswordById()更新密码，并获取返回值*/
        int row = userMapper.updatePasswordById(id,newMd5Passwoed);
        if (row != 1){
            throw new UpdateException("更新数据产生未知异常");
        }

    }

    @Override
    public UserEntity getById(int id) {
       /* 调用userMapper的findById()方法，根据参数uid查询用户数据*/
        UserEntity result = userMapper.findById(id);

        // 判断查询结果是否为null,判断查询结果中的isDelete是否为1
        if (result == null || result.getIsDelete() ==1){
            throw new UserNotFoundException("用户数据不存在");   // 是：抛出UserNotFoundException异常
        }

       /* 创建新的UserEntity对象*/
        UserEntity user = new UserEntity();

        // 将以上查询结果中的username/nickName/sex/age/birthday/email/motto封装到新User对象中
        user.setUsername(result.getUsername());
        user.setNickName(result.getNickName());
        user.setSex(result.getSex());
        user.setAge(result.getAge());
        user.setBirthday(result.getBirthday());
        user.setEmail(result.getEmail());
        user.setMotto(result.getMotto());

        return user; /*返回新的User对象*/
    }

    @Override
    public void changeInfo(int id, String username, UserEntity user) {
        /* 调用userMapper的findById()方法，根据参数id查询用户数据*/
        UserEntity result = userMapper.findById(id);
        if (result == null || result.getIsDelete() == 1){
            throw  new UserNotFoundException("用户数据不存在");
        }

        /*向参数user中补全数据：id*/
        user.setId(id);
        //user.setUsername(username);

        /*调用userMapper的updateInfoById(UserEntity user)方法执行修改，并获取返回值*/
        int row = userMapper.updateInfoById(user);
        if (row != 1){
            throw new UpdateException("更新数据时产生未知异常");
        }

    }

    @Override
    public void changeAvatar(int id, String avatar) {
       /* 调用userMapper的findById()方法，根据参数id查询用户数据*/
        UserEntity result = userMapper.findById(id);
        /*判断查询结果是否为null*/
        if (result == null || result.getIsDelete() == 1){
            throw  new UserNotFoundException("用户数据不存在");
        }

        /*调用userMapper的updateAvatarById()方法执行更新，并获取返回值*/
        int row = userMapper.updateAvatarById(id,avatar);
        /*判断以上返回的受影响行数是否不为1*/
        if (row != 1){
            throw new UpdateException("更新用户头像时产生未知异常");
        }

    }

    /*三次md5算法机密处理*/
    private String getMd5Password(String password,String salt){
        for (int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
