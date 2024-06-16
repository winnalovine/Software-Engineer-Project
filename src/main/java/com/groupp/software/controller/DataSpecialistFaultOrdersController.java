package com.groupp.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.groupp.software.common.R;
import com.groupp.software.entity.DataSpecialistFaultOrders;
import com.groupp.software.entity.Employee;
import com.groupp.software.service.DataSpecialistFaultOrdersService;
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
@CrossOrigin(/*origins = "http://localhost:8081"*/)
@RequestMapping("/dataspecialistfaultorders")
public class DataSpecialistFaultOrdersController {

    @Autowired
    private DataSpecialistFaultOrdersService dataSpecialistFaultOrdersService;


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
    public R draftsubmit(HttpServletRequest request, @RequestBody Map<String, Object> payload){

        log.info("获取的信息：{}",payload.toString());

        //通过这种方式创建的dataSpecialistFaultOrders对象的orderId不会存在与数据库现有的数据重复的情况
        //实现了唯一性。。
        //DataSpecialistFaultOrders dataSpecialistFaultOrders = dataSpecialistFaultOrdersService.createDataSpecialistFaultOrders();

        DataSpecialistFaultOrders dataSpecialistFaultOrders = new DataSpecialistFaultOrders();
        //log.info("order_id:{}", dataSpecialistFaultOrders.getOrderId());


        //获取信息
        dataSpecialistFaultOrders.setOrderStatus((Integer)payload.get("order_status2"));
        dataSpecialistFaultOrders.setFaultType((Integer)payload.get("fault_type2"));
        dataSpecialistFaultOrders.setFaultLevel((Integer)payload.get("fault_level2"));
        dataSpecialistFaultOrders.setNetworkName((String) payload.get("network_name"));
        dataSpecialistFaultOrders.setDeviceName((String) payload.get("device_name"));
        dataSpecialistFaultOrders.setFaultDescription((String) payload.get("fault_description2"));

        //处理日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Object submitDateObj = payload.get("submit_date2");
            log.info("Submit Date Object: {}", submitDateObj);
            if (submitDateObj instanceof String) {
                Date date1 = dateFormat.parse((String) submitDateObj);
                java.sql.Date sql_date1 = new java.sql.Date(date1.getTime());
                dataSpecialistFaultOrders.setSubmitDate(sql_date1);
                log.info("Set Submit Date: {}", sql_date1);
            }
        } catch (ParseException e) {
            log.error("日期转换异常：", e);
        }

        try {
            Object faultOccurrenceDateObj = payload.get("fault_occurrence_date2");
            log.info("Fault Occurrence Date Object: {}", faultOccurrenceDateObj);
            if (faultOccurrenceDateObj instanceof String) {
                Date date2 = dateFormat.parse((String) faultOccurrenceDateObj);
                java.sql.Date sql_date2 = new java.sql.Date(date2.getTime());
                dataSpecialistFaultOrders.setFaultOccurrenceDate(sql_date2);
                log.info("Set Fault Occurrence Date: {}", sql_date2);
            }
        } catch (ParseException e) {
            log.error("日期转换异常：", e);
        }

        // 根据前端传来的值设置城市名称
        Object cityValueObj = payload.get("processing_unit2");
        log.info("City Value Object: {}", cityValueObj);
        String cityName = null;
        if (cityValueObj instanceof Integer) {
            cityName = cityMap.get(cityValueObj.toString());
        } else if (cityValueObj instanceof String) {
            cityName = cityMap.get((String) cityValueObj);
        }
        log.info("Set City Name: {}", cityName);
        dataSpecialistFaultOrders.setProcessingUnit(cityName);

        //处理创建工单对象id
        Object creatorEmployeeIdObj = payload.get("creator_employee_id2");
        log.info("Creator Employee ID Object: {}", creatorEmployeeIdObj);
        if (creatorEmployeeIdObj instanceof Integer) {
            dataSpecialistFaultOrders.setCreatorEmployeeId(((Integer) creatorEmployeeIdObj).longValue());
            log.info("Set Creator Employee ID as Integer: {}", creatorEmployeeIdObj);
        } else if (creatorEmployeeIdObj instanceof String) {
            dataSpecialistFaultOrders.setCreatorEmployeeId(Long.parseLong((String) creatorEmployeeIdObj));
            log.info("Set Creator Employee ID as String: {}", creatorEmployeeIdObj);
        }


        //存入数据库
        dataSpecialistFaultOrdersService.save(dataSpecialistFaultOrders);

        //获取order_id
        Long id = dataSpecialistFaultOrders.getOrderId();
        log.info("id:{}",id);

        //返回数据
        Map<String, Object> result = new HashMap<>();
        //result.put("order_id2", dataSpecialistFaultOrders.getOrderId());
        result.put("order_status2", dataSpecialistFaultOrders.getOrderStatus());
        result.put("submit_date2", dataSpecialistFaultOrders.getSubmitDate());
        result.put("fault_occurrence_date2", dataSpecialistFaultOrders.getFaultOccurrenceDate());
        result.put("processing_unit2", dataSpecialistFaultOrders.getProcessingUnit());
        result.put("creator_employee_id2", dataSpecialistFaultOrders.getCreatorEmployeeId());
        result.put("fault_type2", dataSpecialistFaultOrders.getFaultType());
        result.put("fault_level2", dataSpecialistFaultOrders.getFaultLevel());
        result.put("network_name", dataSpecialistFaultOrders.getNetworkName());
        result.put("device_name", dataSpecialistFaultOrders.getDeviceName());
        result.put("fault_description2", dataSpecialistFaultOrders.getFaultDescription());

        return R.success(result);

    }

}
