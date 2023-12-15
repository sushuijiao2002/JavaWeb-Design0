package com.example.javaweb1.service;

import com.example.javaweb1.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/*用户模块业务层接口*/
public interface UserService {
    void register(UserEntity user);
    UserEntity login(String username,String password);
    void changePassword(int id,String username,String oldPassword,String newPassword);
    UserEntity getById(int id);
    void changeInfo(int id,String username,UserEntity user);

    /**
     * 修改用户头像
     * @param id 用户id
     * @param avatar 用户头像的路径
     */
    void changeAvatar(int id,String avatar);

}
