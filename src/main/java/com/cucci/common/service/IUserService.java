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

    /**
     * 保存新增用户，通知指定用户
     *
     * @param saveForm
     * @param userId
     * @return
     */
    Result inform(UserSaveForm saveForm, String userId);

    /**
     * 保存新增用户，通知所有人
     *
     * @param saveForm
     * @return
     */
    Result broadcast(UserSaveForm saveForm);
}
