package com.example.apigateway;

import com.example.apigateway.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(GatewayApplication.class);
        // 自定义的banner
        springApplication.setBanner(new MyBanner());
        //启动springboot
        springApplication.run(args);
    }
}
