package com.example.springbootadmin;

import com.example.common.banner.MyBanner;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class SpringbootAdminApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(SpringbootAdminApplication.class);
		// 自定义的banner
		springApplication.setBanner(new MyBanner());
		//启动springboot
		springApplication.run(args);
	}

}
