-- in your quartz properties file, you'll need to set org.quartz.jobstore.driverdelegateclass = org.quartz.impl.jdbcjobstore.stdjdbcdelegate
-- 你需要在你的quartz.properties文件中设置org.quartz.jobstore.driverdelegateclass = org.quartz.impl.jdbcjobstore.stdjdbcdelegate
-- stdjdbcdelegate说明支持集群，所有的任务信息都会保存到数据库中，可以控制事物，还有就是如果应用服务器关闭或者重启，任务信息都不会丢失，并且可以恢复因服务器关闭或者重启而导致执行失败的任务
-- this is the script from quartz to create the tables in a mysql database, modified to use innodb instead of myisam
-- 这是来自quartz的脚本，在mysql数据库中创建以下的表，修改为使用innodb而不是myisam
-- 你需要在数据库中执行以下的sql脚本
drop table if exists qrtz_fired_triggers;
drop table if exists qrtz_paused_trigger_grps;
drop table if exists qrtz_scheduler_state;
drop table if exists qrtz_locks;
drop table if exists qrtz_simple_triggers;
drop table if exists qrtz_simprop_triggers;
drop table if exists qrtz_cron_triggers;
drop table if exists qrtz_blob_triggers;
drop table if exists qrtz_triggers;
drop table if exists qrtz_job_details;
drop table if exists qrtz_calendars;
-- 存储每一个已配置的job的详细信息
create table qrtz_job_details (
                                  sched_name        varchar(120) not null,
                                  job_name          varchar(200) not null,
                                  job_group         varchar(200) not null,
                                  description       varchar(250) null,
                                  job_class_name    varchar(250) not null,
                                  is_durable        varchar(1)   not null,
                                  is_nonconcurrent  varchar(1)   not null,
                                  is_update_data    varchar(1)   not null,
                                  requests_recovery varchar(1)   not null,
                                  job_data          blob         null,
                                  primary key (sched_name, job_name, job_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储已配置的trigger的信息
create table qrtz_triggers
(
    sched_name     varchar(120) not null,
    trigger_name   varchar(200) not null,
    trigger_group  varchar(200) not null,
    job_name       varchar(200) not null,
    job_group      varchar(200) not null,
    description    varchar(250) null,
    next_fire_time bigint(13)   null,
    prev_fire_time bigint(13)   null,
    priority       integer      null,
    trigger_state  varchar(16)  not null,
    trigger_type   varchar(8)   not null,
    start_time     bigint(13)   not null,
    end_time       bigint(13)   null,
    calendar_name  varchar(200) null,
    misfire_instr  smallint(2)  null,
    job_data       blob         null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group)
        references qrtz_job_details (sched_name, job_name, job_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储已配置的simple trigger的信息
create table qrtz_simple_triggers
(
    sched_name      varchar(120) not null,
    trigger_name    varchar(200) not null,
    trigger_group   varchar(200) not null,
    repeat_count    bigint(7)    not null,
    repeat_interval bigint(12)   not null,
    times_triggered bigint(10)   not null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储cron trigger，包括cron表达式和时区信息
create table qrtz_cron_triggers
(
    sched_name      varchar(120) not null,
    trigger_name    varchar(200) not null,
    trigger_group   varchar(200) not null,
    cron_expression varchar(120) not null,
    time_zone_id    varchar(80),
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
create table qrtz_simprop_triggers
(
    sched_name    varchar(120)   not null,
    trigger_name  varchar(200)   not null,
    trigger_group varchar(200)   not null,
    str_prop_1    varchar(512)   null,
    str_prop_2    varchar(512)   null,
    str_prop_3    varchar(512)   null,
    int_prop_1    int            null,
    int_prop_2    int            null,
    long_prop_1   bigint         null,
    long_prop_2   bigint         null,
    dec_prop_1    numeric(13, 4) null,
    dec_prop_2    numeric(13, 4) null,
    bool_prop_1   varchar(1)     null,
    bool_prop_2   varchar(1)     null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
--  trigger作为blob类型存储(用于quartz用户用jdbc创建他们自己定制的trigger类型，jobstore并不知道如何存储实例的时候)
create table qrtz_blob_triggers
(
    sched_name    varchar(120) not null,
    trigger_name  varchar(200) not null,
    trigger_group varchar(200) not null,
    blob_data     blob         null,
    primary key (sched_name, trigger_name, trigger_group),
    index (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 以blob类型存储quartz的calendar日历信息,quartz可配置一个日历来指定一个时间范围
create table qrtz_calendars
(
    sched_name    varchar(120) not null,
    calendar_name varchar(200) not null,
    calendar      blob         not null,
    primary key (sched_name, calendar_name)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储已暂停的trigger组的信息
create table qrtz_paused_trigger_grps
(
    sched_name    varchar(120) not null,
    trigger_group varchar(200) not null,
    primary key (sched_name, trigger_group)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储与已触发的trigger相关的状态信息，以及相联job的执行信息
create table qrtz_fired_triggers
(
    sched_name        varchar(120) not null,
    entry_id          varchar(95)  not null,
    trigger_name      varchar(200) not null,
    trigger_group     varchar(200) not null,
    instance_name     varchar(200) not null,
    fired_time        bigint(13)   not null,
    sched_time        bigint(13)   not null,
    priority          integer      not null,
    state             varchar(16)  not null,
    job_name          varchar(200) null,
    job_group         varchar(200) null,
    is_nonconcurrent  varchar(1)   null,
    requests_recovery varchar(1)   null,
    primary key (sched_name, entry_id)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储少量的有关 scheduler的状态信息，和别的 scheduler 实例(假如是用于一个集群中)
create table qrtz_scheduler_state
(
    sched_name        varchar(120) not null,
    instance_name     varchar(200) not null,
    last_checkin_time bigint(13)   not null,
    checkin_interval  bigint(13)   not null,
    primary key (sched_name, instance_name)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
-- 存储程序的非观锁的信息(假如使用了悲观锁)
create table qrtz_locks
(
    sched_name varchar(120) not null,
    lock_name  varchar(40)  not null,
    primary key (sched_name, lock_name)
)
    engine = innodb
    ROW_FORMAT = DYNAMIC;
create index idx_qrtz_j_req_recovery
    on qrtz_job_details (sched_name, requests_recovery);
create index idx_qrtz_j_grp
    on qrtz_job_details (sched_name, job_group);
create index idx_qrtz_t_j
    on qrtz_triggers (sched_name, job_name, job_group);
create index idx_qrtz_t_jg
    on qrtz_triggers (sched_name, job_group);
create index idx_qrtz_t_c
    on qrtz_triggers (sched_name, calendar_name);
create index idx_qrtz_t_g
  on qrtz_triggers (sched_name, trigger_group);
create index idx_qrtz_t_state
  on qrtz_triggers (sched_name, trigger_state);
create index idx_qrtz_t_n_state
  on qrtz_triggers (sched_name, trigger_name, trigger_group, trigger_state);
create index idx_qrtz_t_n_g_state
  on qrtz_triggers (sched_name, trigger_group, trigger_state);
create index idx_qrtz_t_next_fire_time
  on qrtz_triggers (sched_name, next_fire_time);
create index idx_qrtz_t_nft_st
  on qrtz_triggers (sched_name, trigger_state, next_fire_time);
create index idx_qrtz_t_nft_misfire
  on qrtz_triggers (sched_name, misfire_instr, next_fire_time);
create index idx_qrtz_t_nft_st_misfire
  on qrtz_triggers (sched_name, misfire_instr, next_fire_time, trigger_state);
create index idx_qrtz_t_nft_st_misfire_grp
  on qrtz_triggers (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state);
create index idx_qrtz_ft_trig_inst_name
  on qrtz_fired_triggers (sched_name, instance_name);
create index idx_qrtz_ft_inst_job_req_rcvry
  on qrtz_fired_triggers (sched_name, instance_name, requests_recovery);
create index idx_qrtz_ft_j_g
  on qrtz_fired_triggers (sched_name, job_name, job_group);
create index idx_qrtz_ft_jg
  on qrtz_fired_triggers (sched_name, job_group);
create index idx_qrtz_ft_t_g
  on qrtz_fired_triggers (sched_name, trigger_name, trigger_group);
create index idx_qrtz_ft_tg
  on qrtz_fired_triggers (sched_name, trigger_group);
commit;