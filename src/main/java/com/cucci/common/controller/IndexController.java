package com.cucci.common.controller;

import com.cucci.common.annotions.Login;
import com.cucci.common.base.BaseController;
import com.cucci.common.form.UserSaveForm;
import com.cucci.common.service.IndexService;
import com.cucci.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 首页
 *
 * @author shenyw
 **/
@Controller
@ResponseBody
@Api(description = "首页")
public class IndexController extends BaseController {

    @Resource
    private IndexService indexService;

    @ApiOperation(value = "测试拦截器日志", notes = "拦截器日志")
    @GetMapping({"/index", "/"})
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
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @PostMapping("/login")
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
    @ApiOperation(value = "测试BeanValidate", notes = "测试表单验证")
    @PostMapping("/save")
    public Result saveUser(@RequestBody UserSaveForm form) {
        System.out.println(form.toString());
        return Result.createSuccess("保存成功");
    }


}
