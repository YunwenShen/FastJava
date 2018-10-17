package com.cucci.common.annotions;

import java.lang.annotation.*;

/**
 * 登录注解
 *
 * @author shenyw
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
