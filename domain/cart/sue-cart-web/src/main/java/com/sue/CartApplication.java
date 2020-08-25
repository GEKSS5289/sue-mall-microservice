package com.sue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sue
 * @date 2020/8/25 9:59
 */

@SpringBootApplication
@MapperScan(basePackages = "com.imooc.cart.mapper")
@ComponentScan(basePackages = {"com.sue","org.n3r.idworker"})
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
