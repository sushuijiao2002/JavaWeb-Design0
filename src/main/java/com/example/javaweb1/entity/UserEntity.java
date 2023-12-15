package com.example.javaweb1.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * user
 * @author 
 */
public class UserEntity implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 0-女，1-男
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 0-删除，1-已删除
     */
    private Integer isDelete;
    private String motto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getNickName(), user.getNickName()) && Objects.equals(getSex(), user.getSex()) && Objects.equals(getAge(), user.getAge()) && Objects.equals(getBirthday(), user.getBirthday()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getSalt(), user.getSalt()) && Objects.equals(getAvatar(), user.getAvatar()) && Objects.equals(getIsDelete(), user.getIsDelete()) && Objects.equals(getMotto(), user.getMotto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getNickName(), getSex(), getAge(), getBirthday(), getEmail(), getSalt(), getAvatar(), getIsDelete(), getMotto());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isDelete=" + isDelete +
                ", motto='" + motto + '\'' +
                '}';
    }
}
