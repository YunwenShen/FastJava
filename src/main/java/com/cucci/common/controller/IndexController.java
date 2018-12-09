package com.cucci.common.controller;

import com.cucci.common.annotions.Login;
import com.cucci.common.base.BaseController;
import com.cucci.common.service.IndexService;
import com.cucci.common.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 首页
 *
 * @author shenyw
 **/
@Controller
public class IndexController extends BaseController {

    @Resource
    private IndexService indexService;

    @RequestMapping({"/index", "/"})
    @ResponseBody
    public Result index() {
        return indexService.sayHello();
    }

    @RequestMapping("/login")
    @ResponseBody
    @Login
    public Result login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {
        return Result.createSuccess("登陆成功");
    }
}
