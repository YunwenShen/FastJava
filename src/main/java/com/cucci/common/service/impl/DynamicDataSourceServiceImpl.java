package com.cucci.common.service.impl;

import com.cucci.common.annotions.DataSource;
import com.cucci.common.dao.IFooDao;
import com.cucci.common.service.IDynamicDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DynamicDataSourceServiceImpl implements IDynamicDataSourceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IFooDao fooDao;

    @Override
    public Map<String, Object> query() {
        System.out.println(fooDao.selectList(null).get(5));
        return null;
    }

    @Override
    @DataSource("secondary")
    public Map<String, Object> queryBySecondary() {
        System.out.println(fooDao.selectList(null).get(5));
        return null;
    }
}
