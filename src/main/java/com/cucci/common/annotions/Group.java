package com.cucci.common.annotions;

import java.lang.annotation.*;

/**
 * 用于校验表单的组
 * 该表单不能加Valid前缀不然的话会Spring会自动校验
 * @author shenyw@citycloud.com.cn
 **/
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Group {

    Class[] value() default {};
}
