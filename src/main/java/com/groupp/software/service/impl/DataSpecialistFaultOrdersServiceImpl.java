package com.groupp.software.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.groupp.software.entity.DataSpecialistFaultOrders;
import com.groupp.software.entity.MobileSwitchFaultOrders;
import com.groupp.software.mapper.DataSpecialistFaultOrdersMapper;
import com.groupp.software.service.DataSpecialistFaultOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataSpecialistFaultOrdersServiceImpl extends ServiceImpl<DataSpecialistFaultOrdersMapper, DataSpecialistFaultOrders> implements DataSpecialistFaultOrdersService {

   /* //创建DataSpecialistFaultOrders实例化对象，并保证雪花算法生成的order_id的唯一性
    @Override
    public DataSpecialistFaultOrders createDataSpecialistFaultOrders() {
        DataSpecialistFaultOrders dataSpecialistFaultOrders = new DataSpecialistFaultOrders();

        Long id;
        do {
            id = IdWorker.getId();
        } while (isOrderIdExists(id));

        dataSpecialistFaultOrders.setOrderId(id);
        return dataSpecialistFaultOrders;
    }

    //将雪花算法生成的id与数据库进行比对，看是否存在相等的数据。。
    @Override
    public boolean isOrderIdExists(Long id) {
        LambdaQueryWrapper<DataSpecialistFaultOrders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DataSpecialistFaultOrders::getOrderId, id);
        return count(queryWrapper) > 0;
    }*/

    public PageInfo<DataSpecialistFaultOrders> findDataSpecialistFaultOrders(int page, int size, Long employeeId, String pageshow){
        PageHelper.startPage(page,size);
        Map<String ,Object> params= new HashMap<>();
        params.put("pageshow",pageshow);
        params.put("employeeId",employeeId);
        log.info("params{}",params);
        log.info("employeeId:{}",employeeId);
        List<DataSpecialistFaultOrders> dataSpecialistFaultOrdersList=baseMapper.findDataSpecialistFaultOrders(params);
        return new PageInfo<>(dataSpecialistFaultOrdersList);
    }

    //    public Map<String,Object> findByparams(String order_Id,String pageshow)
    public Map<String,Object> findByparams(String order_Id){
        Map<String ,Object> params= new HashMap<>();
//        params.put("pageshow",pageshow);
        params.put("orderId",order_Id);
//        log.info("params{}",params);
        DataSpecialistFaultOrders dataSpecialistFaultOrders =baseMapper.findByparams(params);
        log.info("DataSpecialistFaultOrders:{}",dataSpecialistFaultOrders);
        Map<String,Object> result=new HashMap<>();
        result.put("DataSpecialistFaultOrders",dataSpecialistFaultOrders);
        return result;
    }

}
