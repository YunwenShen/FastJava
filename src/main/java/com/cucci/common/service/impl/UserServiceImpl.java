package com.cucci.common.service.impl;

import com.cucci.common.controller.WebSocketServer;
import com.cucci.common.dao.IUserDao;
import com.cucci.common.entity.User;
import com.cucci.common.form.UserSaveForm;
import com.cucci.common.service.IUserService;
import com.cucci.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenyw
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public Result save(UserSaveForm saveForm) {
        User user = new User();
        user.setHeadUrl("www.taobao.com");
        user.setName("马云");
        user.setAge(58);
        user.setPassword("123456");
        user.insert();
        return Result.createSuccess("保存成功");
    }

    @Override
    public Result inform(UserSaveForm saveForm, String userId) {
        User user = new User();
        user.setHeadUrl("www.taobao.com");
        user.setName(saveForm.getName());
        user.setAge(saveForm.getAge());
        user.setPassword("123456");
        user.insert();
        WebSocketServer.inform(userId, "新增用户:" + user.toString());
        return Result.createSuccess("保存成功");
    }

    @Override
    public Result broadcast(UserSaveForm saveForm) {
        User user = new User();
        user.setHeadUrl("www.taobao.com");
        user.setName(saveForm.getName());
        user.setAge(saveForm.getAge());
        user.setPassword("123456");
        user.insert();
        WebSocketServer.boardcast("新增用户:" + user.toString());
        return Result.createSuccess("保存成功");
    }
}
