package com.groupp.software.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class TransmissionSpecialistFaultOrders {
    //todo 可能要修改值
    private static final long serialVersionUID = 4L;

    private Long orderId;
    private Integer orderStatus;
    private Date submitDate;
    private Date reviewDate;
    private Date completionDate;
    private Date fault_occurrenceDate;
    private String processingUnit;
    private Long creatorEmployeeId;
    private Long handlerEmployeeId;
    private Integer faultType;
    private Integer faultLevel;
    private String faultSegment;
    private String faultDescription;
    private String reviewFeedback;
}
