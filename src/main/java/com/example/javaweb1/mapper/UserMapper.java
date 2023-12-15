package com.example.javaweb1.mapper;

import com.example.javaweb1.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/*用户模块的持久层*/

public interface UserMapper {
    /*
    * 插入用户数据
    * @parma user用户数据
    * @return 受影响的行数（增删查改都受影响的行数作为返回值，可以根据返回值来判断是否执行
    * */
    int insert(UserEntity user);

    UserEntity findByUsername(String username);
    int updatePasswordById(int id,String password);
    UserEntity findById(int id);
   // int updateInfoById(UserEntity user);
    int updateInfoById(UserEntity user);
    int updateAvatarById(@Param("id") int id, @Param("avatar") String avatar);
}
