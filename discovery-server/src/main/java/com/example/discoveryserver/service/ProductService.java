package com.example.discoveryserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.discoveryserver.model.MyProduct;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface ProductService extends IService<MyProduct> {
    @TwoPhaseBusinessAction(name="TCCAction",commitMethod = "commit",rollbackMethod = "cancel")
    String insert(String name);

    boolean commit(BusinessActionContext businessActionContext);

    boolean cancel(BusinessActionContext businessActionContext);

}
