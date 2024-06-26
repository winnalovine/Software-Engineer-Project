package com.groupp.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.groupp.software.common.R;
import com.groupp.software.entity.Employee;
import com.groupp.software.service.EmployeeService;
import com.groupp.software.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j


//todo 可能要修改mapper的资源位置
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    //todo 可能要修改映射位置
    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody Map<String, Object> payload){
        /*
        1.获取职员输入的职工号employeeId和password
        2.按职工号employee在数据库找对应的职工信息
        3.找不到该职工
        4.找到该职工，但是密码输入错误
        5.找到职工且密码正确，把职工号存在HttpSession中名为"employee"的属性中
        */
        System.out.println("Received payload: " + payload);
        // 手动解析数据
        String eidStr = (String) payload.get("Eid");
        Long employeeId = null;
        try {
            employeeId = Long.valueOf(eidStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Eid format: " + eidStr);
        }

        String password = (String) payload.get("passData");

//        1.获取职员输入的职工号employeeId和password

        // 在控制台打印
        System.out.println("职工号: " + employeeId);
        System.out.println("密码: " + password);

//        2.按职工号employee在数据库找对应的职工信息
        LambdaQueryWrapper<Employee> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmployeeId,employeeId);
        Employee employee_ = employeeService.getOne(queryWrapper);

//        3.找不到该职工
        if(employee_ == null){
            return R.error("该职工不存在");
        }
        //        4.找到该职工，但是密码输入错误
        if(!employee_.getPassword().equals(password)){
            return  R.error("密码错误");
        }

        //5.找到职工且密码正确，把职工号存在HttpSession中名为"employee"的属性中
        request.getSession().setAttribute("employee",employeeId);
        // 处理数据并准备返回的结果
        Map<String, Object> result = new HashMap<>();
        result.put("Eid", employeeId);
        result.put("Ename", employee_.getName());
        result.put("passData", password);
        result.put("phone", employee_.getPhoneNumber());
        result.put("Dlevel", employee_.getDepartmentLevel());
        result.put("Dtype",employee_.getDepartmentType());
        result.put("Etype", employee_.getRoleType());
        System.out.println("Received result: " + (result));
        return R.success(result);
    }

    //员工注册
    @PostMapping("/register")
    public R register(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
        log.info("新增员工，员工信息：{}",payload.toString());

        //新建一个employee用来存储注册信息，并更新到数据库
        String eidStr = (String) payload.get("Eid");
        Employee employee=new Employee();
        //手动解析数据
        Long employeeId=null;
        try {
            employeeId = Long.valueOf(eidStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Eid format: " + eidStr);
        }



        employee.setEmployeeId(employeeId);
        employee.setDepartmentType((Integer) payload.get("Dtype"));
        employee.setDepartmentLevel((String) payload.get("Dlevel"));
        employee.setRoleType((Integer) payload.get("Etype"));
        log.info("注册的信息：{}",employee.toString());
        //实现注册
        employeeService.save(employee);
        //处理返回的数据
        Map<String, Object> result = new HashMap<>();
        result.put("Eid", employee.getEmployeeId());
        result.put("Ename", employee.getName());
        result.put("passData", employee.getPassword());
        result.put("phone", employee.getPhoneNumber());
        result.put("Dlevel", employee.getDepartmentLevel());
        result.put("Dtype",employee.getDepartmentType());
        result.put("Etype", employee.getRoleType());
        System.out.println("Received result: " + (result));
        return R.success(result);


    }

    //获得处理人所在部门
    @PostMapping("/getDepartmentTypeOfProcessor")
    public R getDepartmentTypeOfProcessor(HttpServletRequest request, @RequestBody Map<String, Object> payload){
        //todo 解决跨域问题通过session获得员工号
        Long employeeId=1001l;
        Employee employee=employeeServiceImpl.findById(employeeId);
        log.info("employee:{}",employee);
        return R.success(employee);
    }

}
