package com.cucci.common.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 */
@Log4j2
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为===>{}", DynamicContextHolder.getDataSource());
        return DynamicContextHolder.getDataSource();
    }
}
