package com.groupp.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.groupp.software.entity.Employee;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.mapper.EmployeeMapper;
import com.groupp.software.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    public List<Employee> findByparams(String processingUnit,Integer formatType){
        Map<String ,Object> params= new HashMap<>();
//        params.put("pageshow",pageshow);
        params.put("processingUnit",processingUnit);
        params.put("formatType",formatType);
        log.info("params{}",params);
        List<Employee> employees =baseMapper.findByparams(params);
        log.info("employees :{}",employees );

        return employees;
    }

}
