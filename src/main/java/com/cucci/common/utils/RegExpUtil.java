package com.cucci.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author shenyw@citycloud.com.cn
 **/
public class RegExpUtil {

    /**
     * 通过正则解析内容
     *
     * @param content 文本内容
     * @param regExp  正则表达式
     * @return 解析结果
     */
    public static String parse(String content, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(content);
        if (!matcher.find()) {
            return null;
        }
        return matcher.group();
    }

    /**
     * 通过正则表达式解析出符合的内容列表
     *
     * @param content 文本内容
     * @param regExp  正则表达式
     * @return 解析结果列表
     */
    public static List<String> parseList(String content, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * 是否能够匹配结果
     *
     * @param content 文本内容
     * @param regExp  正则表达式
     * @return true表示能够匹配结果
     */
    public static boolean match(String content, String regExp) {
        return content.matches(regExp);
    }

}
