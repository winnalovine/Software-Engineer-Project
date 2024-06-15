package com.groupp.software.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.groupp.software.entity.DataSpecialistFaultOrders;
import com.groupp.software.mapper.DataSpecialistFaultOrdersMapper;
import com.groupp.software.service.DataSpecialistFaultOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DataSpecialistFaultOrdersServiceImpl extends ServiceImpl<DataSpecialistFaultOrdersMapper, DataSpecialistFaultOrders> implements DataSpecialistFaultOrdersService {

    //创建DataSpecialistFaultOrders实例化对象，并保证雪花算法生成的order_id的唯一性
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
    }

}
