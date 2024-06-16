package com.groupp.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MobileSwitchFaultOrdersMapper extends BaseMapper<MobileSwitchFaultOrders>{

    //根据职工号和工单状态查所有满足条件的数据--分页（todo 赶紧这两个好像可以合到一起）
    List<MobileSwitchFaultOrders> findMobileSwitchFaultOrders(Map<String,Object>params);
    //根据工单编号查数据
    MobileSwitchFaultOrders findByparams(Map<String,Object>params);
    MobileSwitchFaultOrders updateByparams(Map<String,Object>params);
}
