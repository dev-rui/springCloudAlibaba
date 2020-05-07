package com.example.casserver;

import com.example.common.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author hrh
 * @Date 2020/5/7 14:02
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CasServerApplication {
    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(CasServerApplication.class);
        // 自定义的banner
        springApplication.setBanner(new MyBanner());
        //启动springboot
        springApplication.run(args);

    }

}
