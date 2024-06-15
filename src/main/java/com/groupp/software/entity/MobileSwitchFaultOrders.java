package com.groupp.software.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MobileSwitchFaultOrders implements Serializable {

    //todo 可能要修改值
    private static final long serialVersionUID = 2L;

    private Long orderId;
    private Integer orderStatus;
    private Date submitDate;
    private Date reviewDate;
    private Date completionDate;
    private Date faultOccurrenceDate;
    private String processingUnit;
    private Long creatorEmployeeId;
    private Long handlerEmployeeId;
    private Integer faultType;
    private Integer faultLevel;
    private String switchId;
    private String faultDescription;
    private String reviewFeedback;

}
