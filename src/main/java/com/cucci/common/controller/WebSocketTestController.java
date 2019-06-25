package com.cucci.common.controller;

import com.cucci.common.annotions.Group;
import com.cucci.common.form.UserSaveForm;
import com.cucci.common.service.IAddGroup;
import com.cucci.common.service.IUserService;
import com.cucci.common.vo.Result;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 测试WebSocket
 *
 * @author shenyw
 **/
@Api(value = "首页")
@AllArgsConstructor
@Controller
public class WebSocketTestController {

    public IUserService userService;

    /**
     * 测试
     *
     * @param model
     * @param sid
     * @return
     */
    @GetMapping("/websocket/test/{userId}")
    public String test(Model model, @PathVariable("userId") String sid) {
        System.out.println(this.hashCode());
        model.addAttribute("userId", sid);
        return "index";
    }

    @RequestMapping("/websocket/inform/{userId}")
    @ResponseBody
    public Result inform(@Group(IAddGroup.class) @RequestBody UserSaveForm saveForm,
                         @PathVariable("userId") String userId) {
        return userService.inform(saveForm, userId);
    }

    @RequestMapping("/websocket/boardcast")
    @ResponseBody
    public Result broadcast(@Group(IAddGroup.class) @RequestBody UserSaveForm saveForm) {
        return userService.broadcast(saveForm);
    }
}
