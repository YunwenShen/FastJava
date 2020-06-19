package com.cucci.common.annotions;

import com.cucci.common.enums.ModuleType;
import com.cucci.common.enums.OperateType;

import java.lang.annotation.*;

/**
 * 操作日志 注解
 *
 * @author shenyw
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {

    /**
     * 操作模块
     *
     * @return
     */
    ModuleType module();

    /**
     * 操作类型
     *
     * @return
     */
    OperateType operate();

    /**
     * 操作描述
     *
     * @return
     */
    String desc();

    /**
     * 是否使用 Spring EL 来解析操作描述
     */
    boolean spel() default false;
}
