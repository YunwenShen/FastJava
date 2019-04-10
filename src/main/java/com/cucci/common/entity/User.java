package com.cucci.common.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户
 *
 * @author shenyw
 **/
@Getter
@Setter
public class User extends Model<User> {

    private static final long serialVersionUID = -632208494633153724L;
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private int age;

    /**
     * 头像地址
     */
    private String headUrl;

    @Override
    protected Serializable pkVal() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}
