package org.kirin.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Hinsteny
 * @version $ID: Application 2019-01-30 14:52 All rights reserved.$
 */
@SpringBootApplication
@PropertySource(value = "classpath:/consumer-config.properties")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
