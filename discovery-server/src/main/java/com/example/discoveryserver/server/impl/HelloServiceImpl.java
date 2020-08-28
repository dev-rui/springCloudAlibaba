/*
package com.example.discoveryserver.server.impl;

import com.example.discoveryapi.server.HelloService;
import com.example.discoveryserver.rocketmq.Producer;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Log4j2
@Service(version = "1.0.0")
@RestController
public class HelloServiceImpl implements HelloService {
    @Value("${sunny:}")
    private  String sunny;
    @Autowired
    private Producer producerRunner;

    @Override
    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello(@RequestBody String name) {
        log.info("invoked name = " + name+" sunny:"+sunny);
        String messageResult=producerRunner.pushmessage(name);
        return "hello " + name+" sunny:"+sunny+"mqresult:"+messageResult;
    }
}
*/
