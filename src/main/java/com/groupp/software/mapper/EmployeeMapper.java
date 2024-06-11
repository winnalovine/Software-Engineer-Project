package com.groupp.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.groupp.software.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
