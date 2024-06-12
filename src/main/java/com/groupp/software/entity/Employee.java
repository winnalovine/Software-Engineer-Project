package com.groupp.software.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 员工实体
 */
@Data
public class Employee implements Serializable {
//    todo 或许uid需要修改
    private static final long serialVersionUID = 1L;

    private Long employeeId;

    private String name;

    private String password;

    private Long phoneNumber;

    private String departmentLevel;

    private Integer departmentType;

    private Integer roleType;



}
