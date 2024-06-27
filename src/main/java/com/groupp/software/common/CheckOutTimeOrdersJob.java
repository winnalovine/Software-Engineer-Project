package com.groupp.software.common;

import com.groupp.software.service.impl.DataSpecialistFaultOrdersServiceImpl;
import com.groupp.software.service.impl.EmployeeServiceImpl;
import com.groupp.software.service.impl.MobileSwitchFaultOrdersServiceImpl;
import com.groupp.software.service.impl.TransmissionSpecialistFaultOrdersServiceImpl;
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
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Component
public class CheckOutTimeOrdersJob implements Job {

    @Autowired
    private MobileSwitchFaultOrdersServiceImpl mobileSwitchFaultOrdersServiceImpl;
    @Autowired
    private DataSpecialistFaultOrdersServiceImpl dataSpecialistFaultOrdersServiceImpl;
    @Autowired
    private TransmissionSpecialistFaultOrdersServiceImpl transmissionSpecialistFaultOrdersServiceImpl;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       log.info("修改移动工单超时项。。。");
       Integer OutTimeOrderMobile =mobileSwitchFaultOrdersServiceImpl.updateOutTimeOrder();
        log.info("超时单数{}",OutTimeOrderMobile);
        log.info("修改移动工单超时项。。。");
        Integer OutTimeOrderData =dataSpecialistFaultOrdersServiceImpl.updateOutTimeOrder();
        log.info("超时单数{}",OutTimeOrderData);
        log.info("修改移动工单超时项。。。");
        Integer OutTimeOrderTrans =transmissionSpecialistFaultOrdersServiceImpl.updateOutTimeOrder();
        log.info("超时单数{}",OutTimeOrderTrans);

    }
}
