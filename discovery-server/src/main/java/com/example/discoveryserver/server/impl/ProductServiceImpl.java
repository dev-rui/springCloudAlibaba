package com.example.discoveryserver.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.discoveryserver.mapper.ProductMapper;
import com.example.discoveryserver.model.MyProduct;
import com.example.discoveryserver.server.ProductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author rui
 */
@Service("productService")
@Log4j2
public class ProductServiceImpl extends ServiceImpl<ProductMapper, MyProduct> implements ProductService {

    private static final String LOGIC_PRIMARY_ID = "TRADE_LOG_ID";
    private ConcurrentHashMap<String, Long> hashMap = new ConcurrentHashMap<>();
    @Override
    public String insert(String name) {
        MyProduct product=new MyProduct(name,new Date(),new Date());
        int id=baseMapper.insert(product);
        if(id>0){
            hashMap.put(LOGIC_PRIMARY_ID, product.getId());
            return "success";
        }
        return "fail";
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("TCC成功,xid:"+xid);
        hashMap.remove(LOGIC_PRIMARY_ID);
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        log.info("TCC失败,xid:"+xid);
        Long Id = hashMap.get(LOGIC_PRIMARY_ID);
        baseMapper.deleteById(Id);
        hashMap.remove(LOGIC_PRIMARY_ID);
        return true;
    }
}
