package com.cucci.common.interceptors;

import com.cucci.common.annotions.Login;
import com.cucci.common.entity.User;
import com.cucci.common.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登陆拦截器
 *
 * @author shenyw
 **/
@Service
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login login = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        boolean isPass = true;
        if (login == null) {
            String userName = request.getHeader("userName");
            String password = request.getHeader("password");
            if (!(userName.equals("test") && password.equals("123456"))) {
                isPass = false;
            }
        }
        // 更新session时长
        if (isPass) {
            // 将登陆用户绑定到RequestContextHolder中可以直接在Service层获取登陆用户
            request.getSession().setAttribute("curUser", new User());
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.write(Result.createError("登陆失败").toString());
                out.flush();
            }
        }
        return super.preHandle(request, response, handler);
    }
}
