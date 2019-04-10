package com.cucci.common.annotions;

import java.lang.annotation.*;

/**
 * 动态数据源配置
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value();
}
