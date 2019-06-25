package com.cucci.common.controller;

import com.cucci.common.service.IDynamicDataSourceService;
import com.cucci.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author shenyw
 */
@RestController
@RequestMapping("/dynamic/datasource")
public class DynamicDataSourceController {

    @Autowired
    private IDynamicDataSourceService dynamicDataSourceService;

    @RequestMapping("/test")
    public Result test() {
        System.out.println(dynamicDataSourceService.query());
        System.out.println(dynamicDataSourceService.queryBySecondary());
        return Result.createSuccess("success");
    }
}
