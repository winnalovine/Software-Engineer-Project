package com.groupp.software.config;

import com.groupp.software.common.CheckOutTimeOrdersJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(CheckOutTimeOrdersJob.class)
                .withIdentity("checkOutTimeOrdersJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("checkOutTimeOrdersTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInHours(24)
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }
}
