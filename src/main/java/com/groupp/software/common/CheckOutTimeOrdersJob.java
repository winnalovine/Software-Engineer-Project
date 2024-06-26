package com.groupp.software.common;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class CheckOutTimeOrdersJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       log.info("job任务执行中。。。");
    }
}
