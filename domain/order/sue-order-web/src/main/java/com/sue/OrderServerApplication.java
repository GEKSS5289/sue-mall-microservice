package com.sue;

import com.sue.item.service.ItemService;
import com.sue.order.fallback.itemservice.ItemCommentsFeignClient;
import com.sue.user.service.AddressService;
import com.sue.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author sue
 * @date 2020/8/24 15:42
 */
@SpringBootApplication
@MapperScan(basePackages = "com.sue.order.mapper")
@ComponentScan(basePackages = {"com.sue","org.n3r.idworker"})
@EnableFeignClients(
        clients = {
                ItemCommentsFeignClient.class,
                ItemService.class,
                UserService.class,
                AddressService.class
        }
)
public class OrderServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class,args);
    }
}
