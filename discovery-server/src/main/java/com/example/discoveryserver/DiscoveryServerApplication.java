package com.example.discoveryserver;

import com.example.common.banner.MyBanner;
import com.example.discoveryserver.rocketmq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author rui
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({MySink.class})
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(DiscoveryServerApplication.class);
        // 自定义的banner
        springApplication.setBanner(new MyBanner());
        //启动springboot
        springApplication.run(args);

    }

}
