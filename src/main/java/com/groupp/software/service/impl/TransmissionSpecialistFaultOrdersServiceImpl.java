package com.groupp.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.groupp.software.common.DateString;
import com.groupp.software.entity.DataSpecialistFaultOrders;
import com.groupp.software.entity.TransmissionSpecialistFaultOrders;
import com.groupp.software.mapper.TransmissionSpecialistFaultOrdersMapper;
import com.groupp.software.service.TransmissionSpecialistFaultOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TransmissionSpecialistFaultOrdersServiceImpl extends ServiceImpl<TransmissionSpecialistFaultOrdersMapper, TransmissionSpecialistFaultOrders> implements TransmissionSpecialistFaultOrdersService {

    public PageInfo<TransmissionSpecialistFaultOrders> findTransmissionSpecialistFaultOrders(int page, int size, Long employeeId, String pageshow){
        PageHelper.startPage(page,size);
        Map<String ,Object> params= new HashMap<>();
        params.put("pageshow",pageshow);
        params.put("employeeId",employeeId);
        log.info("params{}",params);
        log.info("employeeId:{}",employeeId);
        List<TransmissionSpecialistFaultOrders> transmissionSpecialistFaultOrdersList=baseMapper.findTransmissionSpecialistFaultOrders(params);
        return new PageInfo<>(transmissionSpecialistFaultOrdersList);
    }

    //    public Map<String,Object> findByparams(String order_Id,String pageshow)
    public Map<String,Object> findByparams(String order_Id){
        Map<String ,Object> params= new HashMap<>();
//        params.put("pageshow",pageshow);
        params.put("orderId",order_Id);
//        log.info("params{}",params);
        TransmissionSpecialistFaultOrders transmissionSpecialistFaultOrders =baseMapper.findByparams(params);
        log.info("TransmissionSpecialistFaultOrders:{}",transmissionSpecialistFaultOrders);
        Map<String,Object> result=new HashMap<>();
        DateString dateString=new DateString();
        dateString.setOrderId(transmissionSpecialistFaultOrders.getOrderId());
        dateString.setSubmitDate(transmissionSpecialistFaultOrders.getSubmitDate());
        dateString.setReviewDate(transmissionSpecialistFaultOrders.getReviewDate());
        dateString.setCompletionDate(transmissionSpecialistFaultOrders.getCompletionDate());
        dateString.setFaultOccurrenceDate(transmissionSpecialistFaultOrders.getFaultOccurrenceDate());
        dateString.printAll();
        result.put("TransmissionSpecialistFaultOrders",transmissionSpecialistFaultOrders);
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

