package com.cucci.common.constants;

/**
 * 正则表达式常量
 *
 * @author shenyw
 **/
public final class RegExpConstant {

    private static final String IP_ADDRESS = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}\\2";

    public static final String CLASS_NAME = "[^\\.]*?(?=\\$\\$)";
}
