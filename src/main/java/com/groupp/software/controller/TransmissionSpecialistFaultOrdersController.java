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

    // 定义正向映射
    private static final Map<String, String> cityMap = new HashMap<>();
    // 定义反向映射
    private static final Map<String, String> reverseCityMap = new HashMap<>();

    static {
        cityMap.put("0", "广东省");
        cityMap.put("1", "广州市");
        cityMap.put("2", "深圳市");
        cityMap.put("3", "佛山市");
        cityMap.put("4", "东莞市");
        cityMap.put("5", "中山市");
        cityMap.put("6", "珠海市");
        cityMap.put("7", "江门市");
        cityMap.put("8", "肇庆市");
        cityMap.put("9", "惠州市");
        cityMap.put("10", "汕头市");
        cityMap.put("11", "潮州市");
        cityMap.put("12", "揭阳市");
        cityMap.put("13", "汕尾市");
        cityMap.put("14", "茂名市");
        cityMap.put("15", "阳江市");
        cityMap.put("16", "云浮市");
        cityMap.put("17", "韶关市");
        cityMap.put("18", "清远市");
        cityMap.put("19", "梅州市");
        cityMap.put("20", "河源市");
        cityMap.put("21", "湛江市");

        // 初始化反向映射
        reverseCityMap.put("广东省", "0");
        reverseCityMap.put("广州市", "1");
        reverseCityMap.put("深圳市", "2");
        reverseCityMap.put("佛山市", "3");
        reverseCityMap.put("东莞市", "4");
        reverseCityMap.put("中山市", "5");
        reverseCityMap.put("珠海市", "6");
        reverseCityMap.put("江门市", "7");
        reverseCityMap.put("肇庆市", "8");
        reverseCityMap.put("惠州市", "9");
        reverseCityMap.put("汕头市", "10");
        reverseCityMap.put("潮州市", "11");
        reverseCityMap.put("揭阳市", "12");
        reverseCityMap.put("汕尾市", "13");
        reverseCityMap.put("茂名市", "14");
        reverseCityMap.put("阳江市", "15");
        reverseCityMap.put("云浮市", "16");
        reverseCityMap.put("韶关市", "17");
        reverseCityMap.put("清远市", "18");
        reverseCityMap.put("梅州市", "19");
        reverseCityMap.put("河源市", "20");
        reverseCityMap.put("湛江市", "21");
    }

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
                transmissionSpecialistFaultOrders.setFault_occurrenceDate(sql_date1);
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
            return R.success("转换成功！");

        } catch (Exception e) {
            log.error("处理请求时发生异常：", e);
            return R.error("处理请求时发生异常");
        }




    }

    }


