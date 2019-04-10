package com.cucci.common.config;

public class DynamicContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获得当前线程数据源
     *
     * @return 数据源名称
     */
    public static String getDB() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置当前线程数据源
     *
     * @param dataSource 数据源名称
     */
    public static void setDB(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

}