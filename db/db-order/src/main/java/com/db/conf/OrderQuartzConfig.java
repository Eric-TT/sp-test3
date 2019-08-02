package com.db.conf;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.db.quartz.OrderQuartz;

@Configuration
public class OrderQuartzConfig {

	@Bean
	public JobDetail orderjobDetail() {
		return JobBuilder.newJob(OrderQuartz.class).withIdentity("orderQuartz").storeDurably().build();
	}

	@Bean
	public Trigger orderTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
		return TriggerBuilder.newTrigger().forJob(orderjobDetail()) // 触发器绑定任务
				.withIdentity("orderQuartz").withSchedule(scheduleBuilder).build();
	}
}
