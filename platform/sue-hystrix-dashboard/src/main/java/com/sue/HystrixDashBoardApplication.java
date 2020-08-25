package com.sue;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author sue
 * @date 2020/8/25 14:11
 */

@SpringCloudApplication
@EnableHystrixDashboard
public class HystrixDashBoardApplication {
    public static void main(String[] args){
        SpringApplication.run(HystrixDashBoardApplication.class,args);
    }
}
