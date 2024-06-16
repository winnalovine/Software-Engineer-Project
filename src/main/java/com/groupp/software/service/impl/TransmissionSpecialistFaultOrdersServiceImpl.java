package com.groupp.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.entity.TransmissionSpecialistFaultOrders;
import com.groupp.software.mapper.MobileSwitchFaultOrdersMapper;
import com.groupp.software.mapper.TransmissionSpecialistFaultOrdersMapper;
import com.groupp.software.service.MobileSwitchFaultOrdersService;
import com.groupp.software.service.TransmissionSpecialistFaultOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class TransmissionSpecialistFaultOrdersServiceImpl extends ServiceImpl<TransmissionSpecialistFaultOrdersMapper, TransmissionSpecialistFaultOrders> implements TransmissionSpecialistFaultOrdersService {
}

