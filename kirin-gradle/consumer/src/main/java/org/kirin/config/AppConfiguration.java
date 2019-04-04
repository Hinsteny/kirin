package org.kirin.config;

import javax.annotation.Resource;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Hinsteny
 * @version $ID: AppConfiguration 2019-04-01 20:45 All rights reserved.$
 */
@Configuration
public class AppConfiguration {

    @Resource(name = "environment")
    private Environment env;

    @Bean(name = "defaultMQProducer")
    public DefaultMQProducer buildDefaultMQProducer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer(env.getProperty("app.mq.default-producer-name"));
        // Specify name server addresses.
        producer.setNamesrvAddr(env.getProperty("app.mq.url"));
        //Launch the instance.
        producer.start();
        return producer;
    }

}
