package com.example.discoveryserver.controller;




import com.example.discoveryapi.server.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/*@RestController
@RefreshScope
public class ServerController {

    @Reference
    private HelloService helloServiceImpl;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {

        return helloServiceImpl.hello(name);
    }

}*/
