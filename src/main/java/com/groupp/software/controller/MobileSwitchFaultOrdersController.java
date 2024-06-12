package com.groupp.software.controller;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j

@RestController
@CrossOrigin
@RequestMapping("/MobileSwitchFaultOrders")
public class MobileSwitchFaultOrdersController {
    @Autowired
    private MobileSwithFaultOrdersService mobileSwithFaultOrdersService;

    //


}
