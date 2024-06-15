package com.groupp.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MobileSwitchFaultOrdersMapper extends BaseMapper<MobileSwitchFaultOrders>{

    List<MobileSwitchFaultOrders> findMobileSwitchFaultOrders();
    MobileSwitchFaultOrders findByparams(Map<String,Object>params);
}
