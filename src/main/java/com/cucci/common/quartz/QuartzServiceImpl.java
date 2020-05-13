package com.cucci.common.quartz;


import com.cucci.common.entity.BaseJob;
import com.cucci.common.entity.CronJob;
import com.cucci.common.entity.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * quartz service
 *
 * @author shenyw@citycloud.com.cn
 **/
@Service
public class QuartzServiceImpl implements IQuartzService {

    @Autowired
    private Scheduler quartzScheduler;


    @Override
    public void addJob(CronJob cronJob) throws SchedulerException {
        JobDetail jobDetail;
        if (CollectionUtils.isEmpty(cronJob.getParam())) {
            jobDetail = JobBuilder.newJob(cronJob.getClazz()).withIdentity(cronJob.getJobName(), cronJob.getJobGroupName()).build();
        } else {
            jobDetail = JobBuilder.newJob(cronJob.getClazz()).withIdentity(cronJob.getJobName(), cronJob.getJobGroupName()).usingJobData(cronJob.getParam()).build();
        }
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(cronJob.getTriggerName(), cronJob.getTriggerGroupName()).withSchedule(CronScheduleBuilder.cronSchedule(cronJob.getCron())).build();
        quartzScheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void addSimpleJob(SimpleJob simpleJob) throws SchedulerException {
        JobDetail jobDetail;
        if (CollectionUtils.isEmpty(simpleJob.getParam())) {
            jobDetail = JobBuilder.newJob(simpleJob.getClazz()).withIdentity(simpleJob.getJobName(), simpleJob.getJobGroupName()).build();
        } else {
            jobDetail = JobBuilder.newJob(simpleJob.getClazz()).withIdentity(simpleJob.getJobName(), simpleJob.getJobGroupName()).usingJobData(simpleJob.getParam()).build();
        }
        SimpleScheduleBuilder builder;
        if (simpleJob.isRepeat()) {
            builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(simpleJob.getInterval()).repeatForever();
        } else {
            builder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(1);
        }
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(simpleJob.getTriggerName(), simpleJob.getTriggerGroupName()).startNow().startAt(simpleJob.getStartDate()).withSchedule(builder).build();
        quartzScheduler.scheduleJob(jobDetail, trigger);
    }


    @Override
    public void modifyJob(BaseJob oldJob, CronJob newJob) throws SchedulerException {
        CronTrigger trigger = (CronTrigger) quartzScheduler.getTrigger(TriggerKey.triggerKey(oldJob.getTriggerName(), oldJob.getTriggerGroupName()));
        if (trigger == null) {
            throw new NullPointerException("the trigger can't be null");
        }
        JobKey jobKey = JobKey.jobKey(oldJob.getJobName(), oldJob.getJobGroupName());
        TriggerKey triggerKey = TriggerKey.triggerKey(oldJob.getTriggerName(), oldJob.getTriggerGroupName());
        quartzScheduler.pauseTrigger(triggerKey);
        quartzScheduler.unscheduleJob(triggerKey);
        quartzScheduler.deleteJob(jobKey);
        // 修改调度任务不能修改对应的调度类
        newJob.setClazz(oldJob.getClazz());
        newJob.setParam(newJob.getParam());
        this.addJob(newJob);
    }

    @Override
    public void modifySimpleJob(BaseJob oldJob, SimpleJob newJob) throws SchedulerException {
        SimpleTrigger trigger = (SimpleTrigger) quartzScheduler.getTrigger(TriggerKey.triggerKey(oldJob.getTriggerName(), oldJob.getTriggerGroupName()));
        if (trigger == null) {
            throw new NullPointerException("the trigger can't be null");
        }
        JobKey jobKey = JobKey.jobKey(oldJob.getJobName(), oldJob.getJobGroupName());
        TriggerKey triggerKey = TriggerKey.triggerKey(oldJob.getTriggerName(), oldJob.getTriggerGroupName());
        quartzScheduler.pauseTrigger(triggerKey);
        quartzScheduler.unscheduleJob(triggerKey);
        quartzScheduler.deleteJob(jobKey);
        // 修改调度任务不能修改对应的调度类
        newJob.setClazz(oldJob.getClazz());
        newJob.setParam(newJob.getParam());
        this.addSimpleJob(newJob);
    }


    @Override
    public void pauseJob(BaseJob job) throws SchedulerException {
        quartzScheduler.pauseJob(JobKey.jobKey(job.getJobName(), job.getJobGroupName()));
    }

    @Override
    public void resumeJob(BaseJob job) throws SchedulerException {
        quartzScheduler.resumeJob(JobKey.jobKey(job.getJobName(), job.getJobGroupName()));
    }

    @Override
    public void removeJob(BaseJob job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroupName());
        quartzScheduler.pauseTrigger(triggerKey);
        quartzScheduler.unscheduleJob(triggerKey);
        quartzScheduler.deleteJob(jobKey);
    }

    @Override
    public void triggerJob(BaseJob job) throws SchedulerException {
        quartzScheduler.triggerJob(JobKey.jobKey(job.getJobName(), job.getJobGroupName()), job.getParam());
    }


}
