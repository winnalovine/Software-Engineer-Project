package com.groupp.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.mapper.MobileSwitchFaultOrdersMapper;
import com.groupp.software.service.MobileSwitchFaultOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MobileSwitchFaultOrdersServiceImpl extends ServiceImpl<MobileSwitchFaultOrdersMapper, MobileSwitchFaultOrders> implements MobileSwitchFaultOrdersService {

    public PageInfo<MobileSwitchFaultOrders> findMobileSwitchFaultOrders(int page,int size){
        PageHelper.startPage(page,size);
        List<MobileSwitchFaultOrders> mobileSwitchFaultOrdersList=baseMapper.findMobileSwitchFaultOrders();
        return new PageInfo<>(mobileSwitchFaultOrdersList);
    }

    public Map<String,Object> findByparams(String order_Id){
        Map<String ,Object> params= new HashMap<>();
        params.put("orderId",order_Id);
        log.info("orderId:{}",order_Id);
        MobileSwitchFaultOrders mobileSwitchFaultOrders =baseMapper.findByparams(params);
        log.info("MobileSwitchFaultOrders:{}",mobileSwitchFaultOrders);
        Map<String,Object> result=new HashMap<>();
        result.put("MobileSwitchFaultOrders",mobileSwitchFaultOrders);
        return result;
    }

}