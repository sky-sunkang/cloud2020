package com.sunkang.alibabaorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaOrderApplication {

    public static void main(String[] args) {
        try {
            // 启动sentinel服务
            if (!isPortUsing("127.0.0.1", 8849)) {
                Runtime.getRuntime().exec("cmd /k start java -Dserver.port=8849 -Dcsp.sentinel.dashboard.server=localhost:8849 -Dproject.name=sentinel-dashboard -jar C:\\Users\\kang\\Desktop\\server-start\\sentinel-dashboard.jar");
            }

            // 启动nacos服务
            if (!isPortUsing("127.0.0.1", 8848)) {
                Runtime.getRuntime().exec("cmd /k start  C:\\Users\\kang\\Desktop\\server-start\\nacos-server-1.3.2\\bin\\startup.cmd -m standalone");
                Thread.sleep(30000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(AlibabaOrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /***
     *  true:already in using  false:not using
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {

        }
        return flag;

    }
}

