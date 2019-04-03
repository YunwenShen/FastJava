package com.cucci.common.controller;

import com.cucci.common.form.UserSaveForm;
import com.cucci.common.service.IUserService;
import com.cucci.common.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author shenyw
 **/
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @RequestMapping("/add")
    public Result add(@RequestBody UserSaveForm saveForm) {
        return userService.save(saveForm);
    }

    @RequestMapping("/update")
    public Result update(@Validated @RequestBody UserSaveForm saveForm, BindingResult result) {
        return Result.createError(result.toString());
    }
}
