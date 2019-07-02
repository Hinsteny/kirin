package org.kirin.consumer;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Hinsteny
 * @version ConsumerApplication: ConsumerApplication 2019-05-13 16:58 All rights reserved.$
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "org.kirin.consumer")
@PropertySource(value = "classpath:/consumer-config.properties")
@MapperScan(basePackages = {"org.kirin.consumer.mybatis.mapper"})
@NacosPropertySource(dataId = "kirin", autoRefreshed = true)
public class ConsumerApplication {

    public static void main(String[] args) {
        Float x = Float.valueOf("1.2");
        System.out.println(x);
        SpringApplication.run(ConsumerApplication.class, args);
    }

}