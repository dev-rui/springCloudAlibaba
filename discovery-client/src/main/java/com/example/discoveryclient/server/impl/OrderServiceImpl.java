package com.example.discoveryclient.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.discoveryapi.server.HelloService;
import com.example.discoveryclient.mapper.OrderMapper;
import com.example.discoveryclient.model.MyOrder;
import com.example.discoveryclient.rocketmq.Producer;
import com.example.discoveryclient.server.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author rui
 */
@Service("orderService")
@Log4j2
public class OrderServiceImpl extends ServiceImpl<OrderMapper, MyOrder> implements OrderService {

    @DubboReference(version = "1.0.0",timeout = 300000)
    HelloService helloService;
    @Autowired
    private Producer producer;

    boolean flag;

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-seata-example")
    @Override
    public String insertAT(MyOrder myOrder) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        myOrder.setCreateTime(new Date());
        //第一阶段
        baseMapper.insert(myOrder);
        log.info("first:success");
        //第二阶段
        String result = helloService.helloAT(myOrder.getName());
     /*   if (!flag) {
            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
        }*/
        return result;
    }

    @GlobalTransactional
    @Override
    public String insertTCC(MyOrder myOrder) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        myOrder.setCreateTime(new Date());
        //第一阶段
        baseMapper.insert(myOrder);
        log.info("first:success");
        //第二阶段
        String result = helloService.helloTCC(myOrder.getName());
     /*   if (!flag) {
            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
        }*/
        return result;
    }


    @Override
    public String createMessage(String message) {
        String result=producer.pushmessage(message);
        return result;
    }
}
