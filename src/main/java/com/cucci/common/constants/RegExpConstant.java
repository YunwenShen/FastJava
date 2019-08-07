package com.cucci.common.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 正则表达式常量
 *
 * @author shenyw
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegExpConstant {

    public static final String CLASS_NAME = "[^\\.]*?(?=\\$\\$)";
}
