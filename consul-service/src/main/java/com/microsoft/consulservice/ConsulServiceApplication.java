package com.microsoft.consulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient(autoRegister=true)
public class ConsulServiceApplication {

	@RequestMapping("/hello")
    public String home() {
        return "Hello world";
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConsulServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}
