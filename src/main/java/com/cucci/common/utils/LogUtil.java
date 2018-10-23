package com.cucci.common.utils;

import com.cucci.common.vo.Result;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

/**
 * 操作日志工具类
 *
 * @author shenyw@citycloud.com.cn
 **/
public class LogUtil {

    public static boolean contain(String[] ignoreFields, String field) {
        if (ignoreFields.length == 0) {
            return true;
        }
        for (String ignoreField : ignoreFields) {
            if (ignoreField.equals(field)) {
                return true;
            }
        }
        return false;
    }

    public static <T> String compareEach(Object before, Object after, Class<T> clazz, String... ignoreFields) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Assert.notNull(before, "before不能为空");
        Assert.notNull(after, "after不能为空");
        String beforeClassName = before.getClass().getName();
        String afterClassName = after.getClass().getName();
        String clazzName = clazz.getName();
        if (!(beforeClassName.equals(afterClassName) && clazzName.equals(beforeClassName))) {
            throw new RuntimeException("类型错误");
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Object beforeValue = field.get(before);
            Object afterValue = field.get(after);
            String fieldName = field.getName();
            if (beforeValue != null && afterValue != null) {
                if (!beforeValue.equals(afterValue) && contain(ignoreFields, fieldName)) {
                    sb.append(field.getName()).append(":").append(beforeValue).append("->").append(afterValue).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Result result1 = Result.createError("error");
        Result result2 = Result.createSuccess("success");
        try {
            String result = compareEach(result1, new Object(), Object.class);
            System.out.println(result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
