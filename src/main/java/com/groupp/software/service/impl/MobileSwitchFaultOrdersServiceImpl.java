package com.groupp.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.groupp.software.common.DateString;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.mapper.MobileSwitchFaultOrdersMapper;
import com.groupp.software.service.MobileSwitchFaultOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MobileSwitchFaultOrdersServiceImpl extends ServiceImpl<MobileSwitchFaultOrdersMapper, MobileSwitchFaultOrders> implements MobileSwitchFaultOrdersService {

    public PageInfo<MobileSwitchFaultOrders> findMobileSwitchFaultOrders(int page,int size, Long employeeId,String pageshow){
        PageHelper.startPage(page,size);
        Map<String ,Object> params= new HashMap<>();
        params.put("pageshow",pageshow);
//        if(pageshow!="2"&&pageshow!="4")
//        {
//            params.put("employeeId",employeeId);
//        }
        params.put("employeeId",employeeId);
        log.info("params{}",params);
        log.info("employeeId:{}",employeeId);
        List<MobileSwitchFaultOrders> mobileSwitchFaultOrdersList=baseMapper.findMobileSwitchFaultOrders(params);
        return new PageInfo<>(mobileSwitchFaultOrdersList);
    }

//    public Map<String,Object> findByparams(String order_Id,String pageshow)
    public Map<String,Object> findByparams(String order_Id){
        Map<String ,Object> params= new HashMap<>();
//        params.put("pageshow",pageshow);
        params.put("orderId",order_Id);
//        log.info("params{}",params);
        MobileSwitchFaultOrders mobileSwitchFaultOrders =baseMapper.findByparams(params);
        log.info("MobileSwitchFaultOrders:{}",mobileSwitchFaultOrders);
        Map<String,Object> result=new HashMap<>();
        DateString dateString=new DateString();
        dateString.setOrderId(mobileSwitchFaultOrders.getOrderId());
        dateString.setSubmitDate(mobileSwitchFaultOrders.getSubmitDate());
        dateString.setReviewDate(mobileSwitchFaultOrders.getReviewDate());
        dateString.setCompletionDate(mobileSwitchFaultOrders.getCompletionDate());
        dateString.setFaultOccurrenceDate(mobileSwitchFaultOrders.getFaultOccurrenceDate());
        dateString.printAll();
        result.put("MobileSwitchFaultOrders",mobileSwitchFaultOrders);
        result.put("dates",dateString);

        return result;
    }
    public Boolean updateByparams(Map<String ,Object> params){
        log.info("updateparams...:{}",params);
        int rowsAffected= baseMapper.updateByparams(params);
        Boolean isSuccess = rowsAffected > 0;
        return isSuccess;
    }
    //审核用
    public Boolean updateByparamsForApprover(Map<String ,Object> params){
        log.info("updateByparamsForApprover...:{}",params);
        int rowsAffected= baseMapper.updateByparamsForApprover(params);
        Boolean isSuccess = rowsAffected > 0;
        return isSuccess;
    }

    public Integer updateOutTimeOrder(){
        log.info("updateOutTimeOrder...:{}");
        Integer rowsAffected= baseMapper.updateOutTimeOrder();
        return rowsAffected;
    }



}
