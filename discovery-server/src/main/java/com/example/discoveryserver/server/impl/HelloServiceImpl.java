package com.example.discoveryserver.server.impl;

import com.example.discoveryapi.server.HelloService;
import com.example.discoveryserver.server.ProductService;
import com.example.discoveryserver.server.UserService;
import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;


/**
 * @author rui
 */
@Log4j2
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    /*@Value("${sunny:}")
    private  String sunny;
    @Autowired
    private Producer producerRunner;*/

    @Resource
    private UserService userService;

    @Resource
    private ProductService productService;

    @Override
    public String helloTCC(String name) {
        log.info("seataTCC第二阶段");
        String result=productService.insert(name);
        return result;
    }

    @Override
    public String helloAT(@RequestBody String name) {
      /*  log.info("invoked name = " + name+" sunny:"+sunny);
        String messageResult=producerRunner.pushmessage(name);
        return "hello " + name+" sunny:"+sunny+"mqresult:"+messageResult;*/
        log.info("AT全局事务id ：" + RootContext.getXID());
        log.info("seataAT第二阶段");
       String result=userService.insert(name);
        return result;
    }
}

