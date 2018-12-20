package com.cucci.common.utils;

import com.cucci.common.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户工具类
 *
 * @author shenyw@citycloud.com.cn
 **/
public class UserUtil {

    /**
     * 通过在拦截器中设置的数据获取当前登陆用户
     *
     * @return 登陆用户
     */
    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (User) request.getSession().getAttribute("curUser");
    }
}
