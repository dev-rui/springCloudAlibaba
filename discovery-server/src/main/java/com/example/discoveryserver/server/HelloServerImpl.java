package com.example.discoveryserver.server;

import com.example.discoveryapi.server.HelloService;
import com.example.discoveryserver.rocketmq.Producer;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
@Log4j2
@Service
public class HelloServerImpl implements HelloService {
    @Value("${sunny:}")
    private  String sunny;
    @Autowired
    private Producer producerRunner;

    @Override
    public String hello(String name) {
        log.info("invoked name = " + name+" sunny:"+sunny);
        String messageResult=producerRunner.pushmessage(name);
        return "hello " + name+" sunny:"+sunny+"mqresult:"+messageResult;
    }
}
