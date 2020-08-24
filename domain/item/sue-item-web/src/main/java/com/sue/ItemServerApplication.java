package com.sue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author sue
 * @date 2020/8/24 15:42
 */

@SpringBootApplication
@MapperScan(basePackages = "com.sue.item.mapper")
@ComponentScan(basePackages = {"com.sue","org.n3r.idworker"})
public class ItemServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemServerApplication.class,args);
    }
}
