package com.cucci.common.entity;

import com.cucci.common.base.BaseEntity;
import lombok.Data;

/**
 * 用户
 *
 * @author shenyw
 **/
@Data
public class User extends BaseEntity {

    private String userName;

    private String password;

    private String headUrl;
}
