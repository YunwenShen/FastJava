package com.cucci.common.service.impl;

import com.cucci.common.annotions.DataSource;
import com.cucci.common.service.IDynamicDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DynamicDataSourceServiceImpl implements IDynamicDataSourceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Map<String, Object> query() {
        return jdbcTemplate.queryForMap("select * from Users where id = 100000003");
    }

    @Override
    @DataSource("secondary")
    public Map<String, Object> queryBySecondary() {
        return jdbcTemplate.queryForMap("select * from Users where id = 100000003");
    }
}
