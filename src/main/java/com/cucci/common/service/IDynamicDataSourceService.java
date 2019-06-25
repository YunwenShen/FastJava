package com.cucci.common.service;

import java.util.Map;

public interface IDynamicDataSourceService {


    Map<String, Object> query();

    Map<String, Object> queryBySecondary();
}
