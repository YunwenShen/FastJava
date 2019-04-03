package com.cucci.common.service;

import com.cucci.common.form.UserSaveForm;
import com.cucci.common.vo.Result;

/**
 * @author shenyw
 **/
public interface IUserService {

    /**
     * 保存用户
     *
     * @param saveForm
     * @return
     */
    Result save(UserSaveForm saveForm);
}
