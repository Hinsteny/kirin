package org.kirin.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Hinsteny
 * @version $ID: Application 2019-01-30 14:52 All rights reserved.$
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "org.kirin.service")
@PropertySource(value = "classpath:/provider-config.properties")
@MapperScan(basePackages = {"org.kirin.service.mybatis.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
