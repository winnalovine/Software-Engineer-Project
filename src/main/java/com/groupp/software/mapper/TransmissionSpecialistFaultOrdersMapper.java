package com.groupp.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.groupp.software.entity.DataSpecialistFaultOrders;
import com.groupp.software.entity.TransmissionSpecialistFaultOrders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TransmissionSpecialistFaultOrdersMapper extends BaseMapper<TransmissionSpecialistFaultOrders> {

    //根据职工号
    List<TransmissionSpecialistFaultOrders> findTransmissionSpecialistFaultOrders(Map<String,Object> params);
    //根据工单编号
    TransmissionSpecialistFaultOrders findByparams(Map<String,Object>params);

}


