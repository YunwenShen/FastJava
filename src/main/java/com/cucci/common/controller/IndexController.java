package com.cucci.common.controller;

import com.cucci.common.annotions.Login;
import com.cucci.common.base.BaseController;
import com.cucci.common.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 *
 * @author shenyw
 **/
@Controller
public class IndexController extends BaseController {

    @RequestMapping({"/index", "/"})
    @ResponseBody
    public Result index() {
        return Result.createSuccess("hello world");
    }

    @RequestMapping("/login")
    @ResponseBody
    @Login
    public Result login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {
        return Result.createSuccess("登陆成功");
    }
}
