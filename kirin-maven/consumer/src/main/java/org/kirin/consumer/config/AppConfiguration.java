package org.kirin.consumer.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.kirin.consumer.message.listener.TransactionListenerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Hinsteny
 * @version AppConfiguration: AppConfiguration 2019-05-13 17:51 All rights reserved.$
 */
@Configuration
public class AppConfiguration {

    @Resource(name = "environment")
    private Environment env;

//    @Bean(name = "defaultMQProducer")
    public DefaultMQProducer buildDefaultMQProducer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer(env.getProperty("app.mq.default-producer-name"));
        // Specify name server addresses.
        producer.setNamesrvAddr(env.getProperty("app.mq.url"));
        //Launch the instance.
        producer.start();
        return producer;
    }

    @Bean(name = "transactionMQProducer")
    public DefaultMQProducer buildTransactionMQProducer() throws MQClientException {
        //Instantiate with a producer group name.
        TransactionMQProducer  producer = new TransactionMQProducer(env.getProperty("app.mq.default-producer-name"));
        // Specify name server addresses.
        producer.setNamesrvAddr(env.getProperty("app.mq.url"));
        TransactionListener transactionListener = new TransactionListenerImpl();
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000), r -> {
            Thread thread = new Thread(r);
            thread.setName("client-transaction-msg-check-thread");
            return thread;
        });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        //Launch the instance.
        producer.start();
        return producer;
    }

}
