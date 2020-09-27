package com.example.discoveryclient;

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
public class DiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(DiscoveryClientApplication.class);
		// 自定义的banner
		springApplication.setBanner(new MyBanner());
		//启动springboot
		springApplication.run(args);
	}

}
