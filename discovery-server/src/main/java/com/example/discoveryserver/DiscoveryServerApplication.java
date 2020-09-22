package com.example.discoveryserver;

import com.example.common.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author rui
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({Source.class})
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(DiscoveryServerApplication.class);
        // 自定义的banner
        springApplication.setBanner(new MyBanner());
        //启动springboot
        springApplication.run(args);

    }

}
