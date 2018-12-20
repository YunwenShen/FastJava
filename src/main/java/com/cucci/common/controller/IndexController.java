package com.cucci.common.controller;

import com.cucci.common.annotions.Login;
import com.cucci.common.base.BaseController;
import com.cucci.common.form.CodeSaveForm;
import com.cucci.common.form.UserSaveForm;
import com.cucci.common.service.IndexService;
import com.cucci.common.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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


    /**
     * 测试登陆拦截器
     *
     * @param userName
     * @param password
     * @return
     */
    @Login
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {
        return Result.createSuccess("登陆成功");
    }


    /**
     * 测试BeanValidate
     *
     * @param form
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result saveUser(@RequestBody UserSaveForm form) {
        System.out.println(form.toString());
        return Result.createSuccess("保存成功");
    }


    /**
     * 测试BeanValidate
     *
     * @param form
     * @return
     */
    public Result saveCode(@RequestBody CodeSaveForm form) {
        System.out.println(form.toString());
        return Result.createSuccess("保存成功");
    }
}
