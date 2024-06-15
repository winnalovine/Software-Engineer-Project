package com.groupp.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.mapper.MobileSwitchFaultOrdersMapper;
import com.groupp.software.service.MobileSwitchFaultOrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MobileSwitchFaultOrdersServiceImpl extends ServiceImpl<MobileSwitchFaultOrdersMapper, MobileSwitchFaultOrders> implements MobileSwitchFaultOrdersService {

    public PageInfo<MobileSwitchFaultOrders> findMobileSwitchFaultOrders(int page,int size){
        PageHelper.startPage(page,size);
        List<MobileSwitchFaultOrders> mobileSwitchFaultOrdersList=baseMapper.findMobileSwitchFaultOrders();
        return new PageInfo<>(mobileSwitchFaultOrdersList);
    }

}
