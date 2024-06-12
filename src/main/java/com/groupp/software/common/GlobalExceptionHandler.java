package com.groupp.software.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */

//to do 后面还有其他的异常都可以集中处理

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /*
     * 异常处理方法
     * */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R exceptionHandler(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage());

        if (e.getMessage().contains("Duplicate entry")) {
            String[] split = e.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }

}
