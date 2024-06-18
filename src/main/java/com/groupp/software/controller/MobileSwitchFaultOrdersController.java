package com.groupp.software.controller;

import com.github.pagehelper.PageInfo;
import com.groupp.software.common.R;
import com.groupp.software.entity.Employee;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.service.MobileSwitchFaultOrdersService;
import com.groupp.software.service.impl.EmployeeServiceImpl;
import com.groupp.software.service.impl.MobileSwitchFaultOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/mobileswitchfaultOrders")
public class MobileSwitchFaultOrdersController {
    @Autowired
    private MobileSwitchFaultOrdersService mobileSwithFaultOrdersService;

    @Autowired
    private MobileSwitchFaultOrdersServiceImpl mobileSwithFaultOrdersServiceImpl;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    // 定义正向映射
    private static final Map<String, String> cityMap = new HashMap<>();
    // 定义反向映射
    private static final Map<String, String> reverseCityMap = new HashMap<>();

    static {
        cityMap.put("0", "广州省");
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
        reverseCityMap.put("广州省", "0");
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
    public R draftsubmit(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {
        log.info("草稿箱表单信息：{}", payload.toString());

        MobileSwitchFaultOrders mobileSwitchFaultOrders = new MobileSwitchFaultOrders();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (payload.get("order_status1") instanceof Integer) {
            System.out.println("order_status1: " + (Integer) payload.get("order_status1"));
            mobileSwitchFaultOrders.setOrderStatus((Integer) payload.get("order_status1"));
        }

        if (payload.get("submit_date1") instanceof String) {
            System.out.println("submit_date1: " + payload.get("submit_date1"));
            java.sql.Date sql_date1 = new java.sql.Date(dateFormat.parse((String) payload.get("submit_date1")).getTime());
            mobileSwitchFaultOrders.setSubmitDate(sql_date1);
        }

        if (payload.get("fault_occurrence_date1") instanceof String) {
            System.out.println("fault_occurrence_date1: " + payload.get("fault_occurrence_date1"));
            java.sql.Date sql_date2 = new java.sql.Date(dateFormat.parse((String) payload.get("fault_occurrence_date1")).getTime());
            mobileSwitchFaultOrders.setFaultOccurrenceDate(sql_date2);
        }

//        if (payload.get("processing_unit1") != null) {
//            System.out.println("processing_unit1: " + payload.get("processing_unit1").toString());
//            mobileSwitchFaultOrders.setProcessingUnit(payload.get("processing_unit1").toString());
//        } else {
//            throw new RuntimeException("processing_unit1 cannot be null");
//        }

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


        if (payload.get("creator_employee_id1") instanceof Integer) {
            System.out.println("creator_employee_id1: " + Long.valueOf(payload.get("creator_employee_id1").toString()));
            mobileSwitchFaultOrders.setCreatorEmployeeId(Long.valueOf(payload.get("creator_employee_id1").toString()));
        }

        if (payload.get("fault_type1") instanceof Integer) {
            System.out.println("fault_type1: " + (Integer) payload.get("fault_type1"));
            mobileSwitchFaultOrders.setFaultType((Integer) payload.get("fault_type1"));
        }

        if (payload.get("fault_level1") instanceof Integer) {
            System.out.println("fault_level1: " + (Integer) payload.get("fault_level1"));
            mobileSwitchFaultOrders.setFaultLevel((Integer) payload.get("fault_level1"));
        }

        if (payload.get("switch_id") instanceof String) {
            System.out.println("switch_id: " + payload.get("switch_id"));
            mobileSwitchFaultOrders.setSwitchId((String) payload.get("switch_id"));
        }

        if (payload.get("fault_description1") instanceof String) {
            System.out.println("fault_description1: " + payload.get("fault_description1"));
            mobileSwitchFaultOrders.setFaultDescription((String) payload.get("fault_description1"));
        }

        System.out.println(mobileSwitchFaultOrders);
        mobileSwithFaultOrdersService.save(mobileSwitchFaultOrders);

        // 处理返回的数据
        Map<String, Object> result = new HashMap<>();
        result.put("order_status1", mobileSwitchFaultOrders.getOrderStatus());
        result.put("submit_date1", mobileSwitchFaultOrders.getSubmitDate());
        result.put("fault_occurrence_date1", mobileSwitchFaultOrders.getFaultOccurrenceDate());
        // 根据城市名称获取数字值
        String processingUnit = mobileSwitchFaultOrders.getProcessingUnit();
        String processingUnitKey = reverseCityMap.get(processingUnit);
        log.info("城市名称: {}, 对应的数字值: {}", processingUnit, processingUnitKey);
        result.put("processing_unit1", processingUnitKey);

        result.put("creator_employee_id1", mobileSwitchFaultOrders.getCreatorEmployeeId());
        result.put("fault_type1", mobileSwitchFaultOrders.getFaultType());
        result.put("fault_level1", mobileSwitchFaultOrders.getFaultLevel());
        result.put("switch_id", mobileSwitchFaultOrders.getSwitchId());
        result.put("fault_description1", mobileSwitchFaultOrders.getFaultDescription());

        return R.success(result);
    }

    @PostMapping("/listpage")
    public R processing(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {
        log.info("收到的数据：{}", payload.toString());
        String Spage = (String) payload.get("currentPage");
        String Spagesize = (String) payload.get("pageSize");
        String pageshow = (String) payload.get("pageshow");
        log.info("开始获得session中的职工号employeeId。。");
        HttpSession session = request.getSession();
        Long employeeId = (Long) session.getAttribute("employee");
        log.info("职工号employeeId:{}", employeeId);
        employeeId=1001l;
        int page = Integer.parseInt(Spage);
        int pagesize = Integer.parseInt(Spagesize);

        PageInfo<MobileSwitchFaultOrders> result = mobileSwithFaultOrdersServiceImpl.findMobileSwitchFaultOrders(page, pagesize, employeeId, pageshow);
        return R.success(result);
    }

    @PostMapping("/Detailsshow")
    public R processingDetails(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {

        log.info("开始根据id查询。。。");
        log.info("orderId:{}", payload);
        String orderId = (String) payload.get("orderId");
        String pageshow = (String) payload.get("pageshow");
        log.info("orderId:{}", (String) payload.get("orderId"));
        Map<String, Object> result = new HashMap<>();
        result = mobileSwithFaultOrdersServiceImpl.findByparams(orderId);
        log.info("result:{}", result);
        return R.success(result);

    }
    @PostMapping("/draftDetailsshow")
    public R draftDetailsshow(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {

        log.info("开始根据id查询。。。");
        log.info("orderId:{}", payload);
        String orderId = (String) payload.get("orderId");
        String pageshow = (String) payload.get("pageshow");
        log.info("orderId:{}", (String) payload.get("orderId"));
//        int orderId=Integer.parseInt(orderId1);
        Map<String, Object> result = new HashMap<>();
        result = mobileSwithFaultOrdersServiceImpl.findByparams(orderId);
        log.info("result:{}", result);
        log.info("城市:{}",reverseCityMap);
        MobileSwitchFaultOrders mobileSwitchFaultOrders=new MobileSwitchFaultOrders();
        mobileSwitchFaultOrders=(MobileSwitchFaultOrders)result.get("MobileSwitchFaultOrders");
        log.info("城市{}",mobileSwitchFaultOrders.getProcessingUnit());
        String city=reverseCityMap.get(mobileSwitchFaultOrders.getProcessingUnit());
        log.info("city:{}",city);
        mobileSwitchFaultOrders.setProcessingUnit(city);
        result.put("MobileSwitchFaultOrders",mobileSwitchFaultOrders);
        return R.success(result);

    }
    @PostMapping("/draftsaveForm")
    public R draftsaveForm(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        log.info("从草稿箱接收到的数据。。。：{}",payload);
        Map<String, Object> result = new HashMap<>();
        result.put("orderId",Long.valueOf(payload.get("orderId").toString()));
        result.put("orderStatus",Integer.valueOf(payload.get("orderStatus").toString()));
        result.put("submitDate",new java.sql.Date(dateFormat.parse((String) payload.get("submitDate")).getTime()));
        result.put("faultOccurrenceDate",new java.sql.Date(dateFormat.parse((String) payload.get("faultOccurrenceDate")).getTime()));
        String city=(String)payload.get("processingUnit");
        log.info("数字city:{}",city);
        city=cityMap.get(city);
        log.info("汉字city:{}",city);
        result.put("processingUnit",city);
        result.put("faultType",Integer.valueOf(payload.get("faultType").toString()));
        result.put("faultLevel",Integer.valueOf(payload.get("faultLevel").toString()));
        result.put("switchId",(String)payload.get("switchId"));
        result.put("faultDescription",(String)payload.get("faultDescription"));

        Boolean answer=mobileSwithFaultOrdersServiceImpl.updateByparams(result);
        return R.success(answer);
    }
    @PostMapping("/approverDetailsshow")
    public R approverDetailsshow(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {

        log.info("开始根据id查询。。。");
        log.info("orderId:{}", payload);
        String orderId = (String) payload.get("orderId");
        String pageshow = (String) payload.get("pageshow");
        log.info("orderId:{}", (String) payload.get("orderId"));
//        int orderId=Integer.parseInt(orderId1);
        Map<String, Object> result = new HashMap<>();
        result = mobileSwithFaultOrdersServiceImpl.findByparams(orderId);
        log.info("result:{}", result);
        MobileSwitchFaultOrders mobileSwitchFaultOrders=new MobileSwitchFaultOrders();
        mobileSwitchFaultOrders=(MobileSwitchFaultOrders)result.get("MobileSwitchFaultOrders");
        String processingUnit=mobileSwitchFaultOrders.getProcessingUnit();
        log.info("processingUnit:{}",processingUnit);
        Integer formatType=0;
        List<Employee> employees=employeeServiceImpl.findByparams(processingUnit,formatType);
        result.put("Employees",employees);
        return R.success(result);

    }
    @PostMapping("/approverDetailsFail")
    public R approverDetailsFail(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        log.info("从草稿箱接收到的数据。。。：{}",payload);
        Map<String, Object> result = new HashMap<>();
        String answer=(String)payload.get("answer");
        result.put("answer",answer);
        result.put("reviewDate",new java.sql.Date(dateFormat.parse((String) payload.get("reviewDate")).getTime()));
        result.put("orderId",Long.valueOf(payload.get("orderId").toString()));
        result.put("orderStatus",Integer.valueOf(payload.get("orderStatus").toString()));
        //审核失败 "reviewFeedback":"故障说明不清楚"
        result.put("reviewFeedback",(String) payload.get("reviewFeedback"));
        Boolean ans=mobileSwithFaultOrdersServiceImpl.updateByparamsForApprover(result);
        return R.success(ans);
    }
    @PostMapping("/approverDetailsSuccess")
    public R approverDetailsSuccess(HttpServletRequest request, @RequestBody Map<String, Object> payload) throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        log.info("从草稿箱接收到的数据。。。：{}",payload);
        Map<String, Object> result = new HashMap<>();
        String answer=(String)payload.get("answer");
        result.put("answer",answer);
        result.put("reviewDate",new java.sql.Date(dateFormat.parse((String) payload.get("reviewDate")).getTime()));
        result.put("orderId",Long.valueOf(payload.get("orderId").toString()));
        result.put("orderStatus",Integer.valueOf(payload.get("orderStatus").toString()));
        result.put("handlerEmployeeId",Long.valueOf(payload.get("handlerEmployeeId").toString()));

        Boolean ans=mobileSwithFaultOrdersServiceImpl.updateByparamsForApprover(result);
        return R.success(ans);
    }

}