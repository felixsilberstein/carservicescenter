package com.felixsilberstein.config;

import com.felixsilberstein.model.Appointment;
import com.felixsilberstein.service.Chaos;
import com.felixsilberstein.service.ServiceAppointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;


@Configuration
@EnableScheduling
public class RandomSchedulerConfig implements SchedulingConfigurer {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ServiceAppointment serviceAppointment;

    @Autowired
    Chaos chaos;

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    /**
     * Runs ServiceAppointment.create() on a random time period
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        Appointment a = chaos.buildRandomAppointment();
                        serviceAppointment.create(a);
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        Calendar nextExecutionTime =  new GregorianCalendar();
                        Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                        nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                        int nextInterval = ThreadLocalRandom.current().nextInt(1000, 10000);
                        nextExecutionTime.add(Calendar.MILLISECOND, nextInterval);
                        logger.info(String.format("Next random appointment will be created on: ", nextExecutionTime));
                        return nextExecutionTime.getTime();
                    }
                }
        );
    }
}
