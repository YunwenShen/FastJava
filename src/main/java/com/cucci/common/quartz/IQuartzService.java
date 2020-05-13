package com.cucci.common.quartz;


import com.cucci.common.entity.BaseJob;
import com.cucci.common.entity.CronJob;
import com.cucci.common.entity.SimpleJob;
import org.quartz.SchedulerException;

/**
 * quartz
 *
 * @author shenyw@citycloud.com.cn
 **/
public interface IQuartzService {

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    void addJob(CronJob job) throws SchedulerException;

    /**
     * 添加简单任务
     *
     * @param job
     * @throws SchedulerException
     */
    void addSimpleJob(SimpleJob job) throws SchedulerException;

    /**
     * 修改任务
     *
     * @param oldJob
     * @param newJob
     * @return
     * @throws SchedulerException
     */
    void modifyJob(BaseJob oldJob, CronJob newJob) throws SchedulerException;

    /**
     * 修改简单任务
     *
     * @param oldJob
     * @param newJob
     * @throws SchedulerException
     */
    void modifySimpleJob(BaseJob oldJob, SimpleJob newJob) throws SchedulerException;

    /**
     * 暂停任务
     *
     * @param job
     * @throws SchedulerException
     */
    void pauseJob(BaseJob job) throws SchedulerException;

    /**
     * 恢复任务
     *
     * @param job
     * @throws SchedulerException
     */
    void resumeJob(BaseJob job) throws SchedulerException;

    /**
     * 移除任务
     *
     * @param job
     * @throws SchedulerException
     */
    void removeJob(BaseJob job) throws SchedulerException;

    /**
     * 触发任务
     *
     * @param job
     * @throws SchedulerException
     */
    void triggerJob(BaseJob job) throws SchedulerException;

}
