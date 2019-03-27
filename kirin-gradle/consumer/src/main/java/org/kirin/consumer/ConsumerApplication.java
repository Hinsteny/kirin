package org.kirin.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hinsteny
 * @version $ID: Application 2019-01-30 14:52 All rights reserved.$
 */
@SpringBootApplication(scanBasePackages = "org.kirin.consumer")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
