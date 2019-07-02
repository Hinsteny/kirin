package org.kirin.service.config;

import javax.annotation.Resource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.kirin.service.message.UserMessageConsumerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Hinsteny
 * @version $ID: AppConfiguration 2019-04-02 19:45 All rights reserved.$
 */
@Configuration
public class AppConfiguration {

    @Resource(name = "environment")
    private Environment env;

    @Bean
    public MessageListenerConcurrently createMessageListenerConcurrently() {
        return new UserMessageConsumerService();
    }

    @Bean(name = "defaultMQPushConsumer")
    public DefaultMQPushConsumer buildDefaultMQPushConsumer(MessageListenerConcurrently messageListenerConcurrently) throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "*");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //set to broadcast mode
        consumer.setMessageModel(MessageModel.BROADCASTING);

        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(messageListenerConcurrently);
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }

}
