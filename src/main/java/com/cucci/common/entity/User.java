package com.cucci.common.entity;

import com.cucci.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户
 *
 * @author shenyw
 **/
@Getter
@Setter
public class User extends BaseEntity {

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
}
