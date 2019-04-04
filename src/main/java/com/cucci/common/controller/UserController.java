package com.cucci.common.controller;

import com.cucci.common.annotions.Group;
import com.cucci.common.form.UserSaveForm;
import com.cucci.common.service.IAddGroup;
import com.cucci.common.service.IUpdateGroup;
import com.cucci.common.service.IUserService;
import com.cucci.common.vo.Result;
import lombok.AllArgsConstructor;
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
    @Group(IUpdateGroup.class)
    public Result add(@Group(IAddGroup.class) @RequestBody UserSaveForm saveForm) {
        return userService.save(saveForm);
    }
}
