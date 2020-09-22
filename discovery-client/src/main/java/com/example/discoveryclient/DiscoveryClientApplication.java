package com.example.discoveryclient;

import com.example.common.banner.MyBanner;
import com.example.discoveryclient.server.MySink;
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
public class DiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(DiscoveryClientApplication.class);
		// 自定义的banner
		springApplication.setBanner(new MyBanner());
		//启动springboot
		springApplication.run(args);
	}

}
