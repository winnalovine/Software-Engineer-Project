package com.groupp.software.controller;

import com.groupp.software.common.R;
import com.groupp.software.entity.TransmissionSpecialistFaultOrders;
import com.groupp.software.service.TransmissionSpecialistFaultOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/transmissionspecialistfaultOrders")
public class TransmissionSpecialistFaultOrdersController {

    @Autowired
    private TransmissionSpecialistFaultOrdersService transmissionSpecialistFaultOrdersService;



    @PostMapping("/draftsubmit")
    public R draftsubmit(HttpServletRequest request, @RequestBody Map<String,Object>payload) {
        log.info("草稿箱表单信息：{}", payload.toString());
        TransmissionSpecialistFaultOrders transmissionSpecialistFaultOrders = new TransmissionSpecialistFaultOrders();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Integer
            //bject orderStatusObj = payload.get("order_status3");
            if (payload.get("order_status3") instanceof Integer) {
                System.out.println("order_status1: " + (Integer) payload.get("order_status3"));
                transmissionSpecialistFaultOrders.setOrderStatus((Integer) payload.get("order_status3"));
            }
            //Date
//        Object orderStatusObj = payload.get("submit_date3");
            if (payload.get("submit_date3") instanceof String) {
                System.out.println("submit_date3: " + payload.get("submit_date3"));
                java.sql.Date sql_date1 = new java.sql.Date(dateFormat.parse((String) payload.get("submit_date3")).getTime());
                transmissionSpecialistFaultOrders.setSubmitDate(sql_date1);
            }

            //        Date
//        Object orderStatusObj = payload.get("fault_occurrence_date3");
            if (payload.get("fault_occurrence_date3") instanceof String) {
                System.out.println("fault_occurrence_date3: " + payload.get("fault_occurrence_date3"));
                java.sql.Date sql_date1 = new java.sql.Date(dateFormat.parse((String) payload.get("fault_occurrence_date3")).getTime());
                transmissionSpecialistFaultOrders.setFaultOccurrenceDate(sql_date1);
            }

//        String
//        Object orderStatusObj = payload.get("processing_unit3");
            Object cityValueObj = payload.get("processing_unit3");
            log.info("City Value Object: {}", cityValueObj);
            String cityName = null;
            if (cityValueObj instanceof Integer) {
                cityName = cityMap.get(cityValueObj.toString());
            } else if (cityValueObj instanceof String) {
                cityName = cityMap.get((String) cityValueObj);
            }
            log.info("Set City Name: {}", cityName);
            transmissionSpecialistFaultOrders.setProcessingUnit(cityName);

//        Long
//        Object orderStatusObj = payload.get("creator_employee_id3:");
            if (payload.get("creator_employee_id3") instanceof Integer) {
                System.out.println("creator_employee_id3: " + Long.valueOf(payload.get("creator_employee_id3").toString()));
                transmissionSpecialistFaultOrders.setCreatorEmployeeId(Long.valueOf(payload.get("creator_employee_id3").toString()));
            }

//        Integer
//        Object orderStatusObj = payload.get("fault_type3:");
            if (payload.get("fault_type3") instanceof Integer) {
                System.out.println("fault_type3: " + (Integer) payload.get("fault_type3"));
                transmissionSpecialistFaultOrders.setFaultType((Integer) payload.get("fault_type3"));
            }

//        Integer
//        Object orderStatusObj =payload.get("fault_level3");
            if (payload.get("fault_level3") instanceof Integer) {
                System.out.println("fault_level3: " + (Integer) payload.get("fault_level3"));
                transmissionSpecialistFaultOrders.setFaultLevel((Integer) payload.get("fault_level3"));
            }

//        String
//        Object orderStatusObj = payload.get("fault_segment:");
            if (payload.get("fault_segment") instanceof String) {
                System.out.println("fault_segment: " + payload.get("fault_segment"));
                transmissionSpecialistFaultOrders.setFaultSegment((String) payload.get("fault_segment"));
            }

//        String
//        Object orderStatusObj = payload.get("fault_description3");
            if (payload.get("fault_description3") instanceof String) {
                System.out.println("fault_description3: " + payload.get("fault_description3"));
                transmissionSpecialistFaultOrders.setFaultDescription((String) payload.get("fault_description3"));
            }

            //存入数据库
            System.out.println(transmissionSpecialistFaultOrders);
            transmissionSpecialistFaultOrdersService.save(transmissionSpecialistFaultOrders);
            // 处理返回的数据
            Map<String, Object> result = new HashMap<>();
            result.put("order_status3", transmissionSpecialistFaultOrders.getOrderStatus());
            result.put("submit_date3", transmissionSpecialistFaultOrders.getSubmitDate());
            result.put("fault_occurrence_date3", transmissionSpecialistFaultOrders.getFaultOccurrenceDate());
            // 根据城市名称获取数字值
            String processingUnit = transmissionSpecialistFaultOrders.getProcessingUnit();
            String processingUnitKey = reverseCityMap.get(processingUnit);
            log.info("城市名称: {}, 对应的数字值: {}", processingUnit, processingUnitKey);
            result.put("processing_unit3", processingUnitKey);

            result.put("creator_employee_id3", transmissionSpecialistFaultOrders.getCreatorEmployeeId());
            result.put("fault_type3", transmissionSpecialistFaultOrders.getFaultType());
            result.put("fault_level3", transmissionSpecialistFaultOrders.getFaultLevel());
            result.put("fault_segment", transmissionSpecialistFaultOrders.getFaultSegment());
            result.put("fault_description3", transmissionSpecialistFaultOrders.getFaultDescription());

            return R.success(result);

        } catch (Exception e) {
            log.error("处理请求时发生异常：", e);
            return R.error("处理请求时发生异常");
        }




    }

    }


