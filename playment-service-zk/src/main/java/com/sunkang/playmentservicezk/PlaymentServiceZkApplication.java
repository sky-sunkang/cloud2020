package com.sunkang.playmentservicezk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlaymentServiceZkApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaymentServiceZkApplication.class, args);
    }

}
