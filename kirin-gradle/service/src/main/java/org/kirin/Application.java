package org.kirin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Hinsteny
 * @version $ID: Application 2019-01-30 14:52 All rights reserved.$
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.kirin.mybatis.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
