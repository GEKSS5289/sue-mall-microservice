package com.sue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author sue
 * @date 2020/8/24 11:37
 */

@SpringBootApplication
public class EurekaRegisterApplication {
    public static void main(String[] args) {
       new SpringApplicationBuilder(EurekaRegisterApplication.class)
               .web(WebApplicationType.SERVLET)
               .run(args);
    }
}
