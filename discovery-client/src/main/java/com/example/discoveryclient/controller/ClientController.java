package com.example.discoveryclient.controller;



import com.example.discoveryclient.server.Client;
import com.example.discoveryclient.retryer.TechlogRetryer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ClientController {

    @Autowired
    Client client;
    //LoadBalancerClient loadBalancerClient;

    @GetMapping("/test")
    public String test() {
       /*通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用

        ServiceInstance serviceInstance = loadBalancerClient.choose("discovery-server");
        String url = serviceInstance.getUri() + "/hello?name=" + "didi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);*/


        String result = client.hello("didi");
        return "Return : " + result;
    }

    @GetMapping("/call")
    @TechlogRetryer(retryThrowable = Exception.class,waitMsec = 3,maxAttempt = 3)
    public String call() {
        String  result = client.hello("test_method");
        return "Return : " + result;
    }
}
