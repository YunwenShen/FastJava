package com.cucci.common.service.impl;

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
//        userDao.insert(user);
        return Result.createSuccess("保存成功");
    }


}
