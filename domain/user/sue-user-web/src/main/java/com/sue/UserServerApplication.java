package com.sue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author sue
 * @date 2020/8/24 14:56
 */

@SpringBootApplication
@MapperScan(basePackages = "com.sue.user.mapper")
@ComponentScan(basePackages = {"com.sue","org.n3r.idworker"})
@EnableCircuitBreaker
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
    }
}
