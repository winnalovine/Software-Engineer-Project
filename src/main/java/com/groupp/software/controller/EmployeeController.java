package com.groupp.software.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.groupp.software.common.R;
import com.groupp.software.entity.Employee;
import com.groupp.software.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j


//todo 可能要修改mapper的资源位置
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    //todo 可能要修改映射位置
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        /*
        1.获取职员输入的职工号employeeId和password
        2.按职工号employee在数据库找对应的职工信息
        3.找不到该职工
        4.找到该职工，但是密码输入错误
        5.找到职工且密码正确，把职工号存在HttpSession中名为"employee"的属性中
        */
//        1.获取职员输入的职工号employeeId和password
        Long employeeId = employee.getEmployeeId();
        String password = employee.getPassword();

//        2.按职工号employee在数据库找对应的职工信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmployeeId, employeeId);
        Employee employee_ = employeeService.getOne(queryWrapper);


//        3.找不到该职工
        if (employee_ == null) {
            return R.error("该职工不存在");
        }
        //        4.找到该职工，但是密码输入错误
        if (!employee_.getPassword().equals(password)) {
            return R.error("密码错误");
        }
        //        5.找到职工且密码正确，把职工号存在HttpSession中名为"employee"的属性中
        request.getSession().setAttribute("employee", employeeId);
        return R.success(employee_);

    }

    //员工注册
    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}",employee.toString());

        employeeService.save(employee);

        return R.success("注册成功");
        /*//获取注册信息
        Long employeeId = employee.getEmployeeId();
        //按职工号在数据库查找信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmployeeId, employeeId);
        Employee employee_ = employeeService.getOne(queryWrapper);

        //没有找到，则可以进行注册
        if (employee_ == null) {
            //将注册的员工信息保存到数据库中
            employeeService.save(employee);
            //       注册成功，把职工号存在HttpSession中名为"employee"的属性中
            request.getSession().setAttribute("employee", employeeId);
            return R.success(employee);
        }

        //找到相同的职工号，则注册失败
        else {
            return R.error("该职工号信息已经存在，注册失败");
        }*/


    }


}
