package com.cucci.common.config;

/**
 * 多数据源上下文管理器
 *
 * @author shenyw
 */
public class DynamicContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获得当前线程数据源
     *
     * @return 数据源名称
     */
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置当前线程数据源
     *
     * @param dataSource 数据源名称
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 清除多数据源的上下文
     */
    public static void removeDataSource() {
        CONTEXT_HOLDER.remove();
    }

}