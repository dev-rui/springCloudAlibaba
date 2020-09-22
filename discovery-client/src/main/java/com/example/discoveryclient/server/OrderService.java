package com.example.discoveryclient.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.discoveryclient.model.MyOrder;

public interface OrderService extends IService<MyOrder> {

    String insertAT(MyOrder myOrder);

    String insertTCC(MyOrder myOrder);
}
