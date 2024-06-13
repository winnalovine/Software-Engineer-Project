package com.groupp.software.controller;

import com.groupp.software.common.R;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.service.MobileSwitchFaultOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/mobileswitchfaultOrders")
public class MobileSwitchFaultOrdersController {
    @Autowired
    private MobileSwitchFaultOrdersService mobileSwitchFaultOrdersService;

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
    public R draftsubmit(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
        log.info("草稿箱表单信息：{}", payload.toString());

        MobileSwitchFaultOrders mobileSwitchFaultOrders = new MobileSwitchFaultOrders();

        try {
            // 确保 order_status1 是 String 类型
            Object orderStatusObj = payload.get("order_status1");
            log.info("Order Status Object: {}", orderStatusObj);
            if (orderStatusObj instanceof Integer) {
                mobileSwitchFaultOrders.setOrderStatus((Integer) orderStatusObj);
                log.info("Set Order Status as Integer: {}", orderStatusObj);
            } else if (orderStatusObj instanceof String) {
                mobileSwitchFaultOrders.setOrderStatus(Integer.parseInt((String) orderStatusObj));
                log.info("Set Order Status as String: {}", orderStatusObj);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Object submitDateObj = payload.get("submit_date1");
                log.info("Submit Date Object: {}", submitDateObj);
                if (submitDateObj instanceof String) {
                    Date date1 = dateFormat.parse((String) submitDateObj);
                    java.sql.Date sql_date1 = new java.sql.Date(date1.getTime());
                    mobileSwitchFaultOrders.setSubmitDate(sql_date1);
                    log.info("Set Submit Date: {}", sql_date1);
                }
            } catch (ParseException e) {
                log.error("日期转换异常：", e);
            }

            try {
                Object faultOccurrenceDateObj = payload.get("fault_occurrence_date1");
                log.info("Fault Occurrence Date Object: {}", faultOccurrenceDateObj);
                if (faultOccurrenceDateObj instanceof String) {
                    Date date2 = dateFormat.parse((String) faultOccurrenceDateObj);
                    java.sql.Date sql_date2 = new java.sql.Date(date2.getTime());
                    mobileSwitchFaultOrders.setFaultOccurrenceDate(sql_date2);
                    log.info("Set Fault Occurrence Date: {}", sql_date2);
                }
            } catch (ParseException e) {
                log.error("日期转换异常：", e);
            }

            // 根据前端传来的值设置城市名称
            Object cityValueObj = payload.get("processing_unit1");
            log.info("City Value Object: {}", cityValueObj);
            String cityName = null;
            if (cityValueObj instanceof Integer) {
                cityName = cityMap.get(cityValueObj.toString());
            } else if (cityValueObj instanceof String) {
                cityName = cityMap.get((String) cityValueObj);
            }
            log.info("Set City Name: {}", cityName);
            mobileSwitchFaultOrders.setProcessingUnit(cityName);

            Object creatorEmployeeIdObj = payload.get("creator_employee_id1");
            log.info("Creator Employee ID Object: {}", creatorEmployeeIdObj);
            if (creatorEmployeeIdObj instanceof Integer) {
                mobileSwitchFaultOrders.setCreatorEmployeeId(((Integer) creatorEmployeeIdObj).longValue());
                log.info("Set Creator Employee ID as Integer: {}", creatorEmployeeIdObj);
            } else if (creatorEmployeeIdObj instanceof String) {
                mobileSwitchFaultOrders.setCreatorEmployeeId(Long.parseLong((String) creatorEmployeeIdObj));
                log.info("Set Creator Employee ID as String: {}", creatorEmployeeIdObj);
            }

            // 确保 fault_type1 和 fault_level1 是 String 类型
            Object faultTypeObj = payload.get("fault_type1");
            log.info("Fault Type Object: {}", faultTypeObj);
            if (faultTypeObj instanceof Integer) {
                mobileSwitchFaultOrders.setFaultType((Integer) faultTypeObj);
                log.info("Set Fault Type as Integer: {}", faultTypeObj);
            } else if (faultTypeObj instanceof String) {
                mobileSwitchFaultOrders.setFaultType(Integer.parseInt((String) faultTypeObj));
                log.info("Set Fault Type as String: {}", faultTypeObj);
            }

            Object faultLevelObj = payload.get("fault_level1");
            log.info("Fault Level Object: {}", faultLevelObj);
            if (faultLevelObj instanceof Integer) {
                mobileSwitchFaultOrders.setFaultLevel((Integer) faultLevelObj);
                log.info("Set Fault Level as Integer: {}", faultLevelObj);
            } else if (faultLevelObj instanceof String) {
                mobileSwitchFaultOrders.setFaultLevel(Integer.parseInt((String) faultLevelObj));
                log.info("Set Fault Level as String: {}", faultLevelObj);
            }

            mobileSwitchFaultOrders.setSwitchId((String) payload.get("switch_id"));
            mobileSwitchFaultOrders.setFaultDescription((String) payload.get("fault_description1"));
            log.info("Set Switch ID: {}", payload.get("switch_id"));
            log.info("Set Fault Description: {}", payload.get("fault_description1"));

            log.info("MobileSwitchFaultOrders: {}", mobileSwitchFaultOrders);

            mobileSwitchFaultOrdersService.save(mobileSwitchFaultOrders);
            log.info("Saved MobileSwitchFaultOrders to database.");

            // 处理返回的数据
            Map<String, Object> result = new HashMap<>();
            result.put("order_status1", mobileSwitchFaultOrders.getOrderStatus());
            result.put("submit_date1", mobileSwitchFaultOrders.getSubmitDate());
            result.put("fault_occurrence_date1", mobileSwitchFaultOrders.getFaultOccurrenceDate());
            //result.put("processing_unit1", mobileSwitchFaultOrders.getProcessingUnit());
            result.put("creator_employee_id1", mobileSwitchFaultOrders.getCreatorEmployeeId());
            result.put("fault_type1", mobileSwitchFaultOrders.getFaultType());
            result.put("fault_level1", mobileSwitchFaultOrders.getFaultLevel());
            result.put("switch_id", mobileSwitchFaultOrders.getSwitchId());
            result.put("fault_description1", mobileSwitchFaultOrders.getFaultDescription());

            // 根据城市名称获取数字值
            String processingUnit = mobileSwitchFaultOrders.getProcessingUnit();
            String processingUnitKey = reverseCityMap.get(processingUnit);
            log.info("城市名称: {}, 对应的数字值: {}", processingUnit, processingUnitKey);
            result.put("processing_unit1", processingUnitKey);

            return R.success(result);

        } catch (Exception e) {
            log.error("处理请求时发生异常：", e);
            return R.error("处理请求时发生异常");
        }
    }
}
