package com.cucci.common.quartz;

import com.cucci.common.entity.BaseJob;
import com.cucci.common.entity.Job;
import com.cucci.common.entity.SimpleJob;

/**
 * 定时任务接口
 *
 * @author shenyw
 */
public interface IQuartzService {

    /**
     * 添加任务
     *
     * @param job
     */
    void addJob(Job job);

    /**
     * 添加简单任务
     *
     * @param job
     */
    void addSimpleJob(SimpleJob job);

    /**
     * 暂停任务
     *
     * @param job
     */
    void pauseJob(BaseJob job);

    /**
     * 恢复任务
     *
     * @param job
     */
    void resumeJob(BaseJob job);

    /**
     * 移除任务
     *
     * @param job
     */
    void removeJob(BaseJob job);

    /**
     * 触发任务
     *
     * @param job
     */
    void triggerJob(BaseJob job);

    /**
     * 修改任务
     *
     * @param oldJob
     * @param newJob
     * @return
     */
    boolean modifyJob(Job oldJob, Job newJob);

    /**
     * 修改简单任务
     *
     * @param oldJob
     * @param newJob
     * @return
     */
    boolean modifySimpleJob(SimpleJob oldJob, SimpleJob newJob);


    /**
     * 开始任务
     */
    void startSchedule();

    /**
     * 取消计划
     */
    void shutdownSchedule();

}
