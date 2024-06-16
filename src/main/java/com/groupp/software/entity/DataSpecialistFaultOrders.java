package com.groupp.software.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class DataSpecialistFaultOrders {

    //todo 可能需要修改maybe
    private static final long serialVersionUID = 2L;

    //通过雪花自增算法来新增orderId
    //@TableId(value="order_id",type= IdType.ASSIGN_ID)
    private Long orderId;//工单编号

    private Integer orderStatus;//工单编号
    private Date submitDate;//提交时间
    private Date reviewDate;//审核时间
    private Date completionDate;//处理完成时间
    private Date faultOccurrenceDate;//故障发生时间
    private String processingUnit;//处理单位
    private Long creatorEmployeeId;//创建工单对象
    private Long handlerEmployeeId;//处理人
    private Integer faultType;//故障类型
    private Integer faultLevel;//故障等级
    private String networkName;//网络名称
    private String deviceName;//设备名称
    private String faultDescription;//故障现象
    private String reviewFeedback;//审核不通过理由反馈
}
