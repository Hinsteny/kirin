package org.kirin.service;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author Hinsteny
 * @version $ID: Application 2019-05-06 14:52 All rights reserved.$
 */
@SpringBootApplication(scanBasePackages = "org.kirin.service.tasks")
//@PropertySource(value = "classpath:/provider-config.properties")
@EnableScheduling
//@EnableDubbo(scanBasePackages = "org.kirin.service")
//@MapperScan(basePackages = {"org.kirin.service.mybatis.mapper"})
//@NacosPropertySource(dataId = "kirin", autoRefreshed = true)
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
