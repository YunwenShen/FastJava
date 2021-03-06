package com.cucci.common.service.impl;

import com.cucci.common.service.IndexService;
import com.cucci.common.vo.Result;
import org.springframework.stereotype.Service;

/**
 * 首页接口实现类
 *
 * @author shenyw
 **/

@Service
public class IndexServiceImpl implements IndexService {


    @Override
    public Result sayHello() {
        return Result.createSuccess("hello world");
    }
}
