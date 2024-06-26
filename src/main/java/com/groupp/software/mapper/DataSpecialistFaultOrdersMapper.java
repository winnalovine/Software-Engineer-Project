package com.groupp.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.groupp.software.entity.DataSpecialistFaultOrders;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataSpecialistFaultOrdersMapper extends BaseMapper<DataSpecialistFaultOrders> {
    //根据职工号
    List<DataSpecialistFaultOrders> findDataSpecialistFaultOrders(Map<String,Object>params);
    //根据工单编号
    DataSpecialistFaultOrders findByparams(Map<String,Object>params);
    //根据工单编号
    Integer updateByparams(Map<String,Object>params);

    Integer updateByparamsForApprover(Map<String,Object>params);
}
