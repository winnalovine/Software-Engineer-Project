package com.groupp.software;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
//用来产生日志 log
@SpringBootApplication
public class SoftwareApplplicathon {
    public static void main(String[] args) {
        SpringApplication.run(SoftwareApplplicathon.class,args);
        log.info("项目启动成功！");
    }
}
