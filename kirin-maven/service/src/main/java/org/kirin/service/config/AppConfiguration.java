package org.kirin.service.config;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Hinsteny
 * @version $ID: AppConfiguration 2019-04-02 19:45 All rights reserved.$
 */
@Configuration
public class AppConfiguration {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource(name = "environment")
    private Environment env;

    /**
     * 创建一个用户消息消费者
     * @return
     * @throws MQClientException
     */
//    @Bean(name = "userMessageConsumer")
    public DefaultMQPushConsumer buildUserMQPullConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(message -> {
                try {
                    MessageQueue messageQueue = context.getMessageQueue();
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New Message: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("exception", e);
                }
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }

    /**
     * 创建一个消费有序消息消费者
     * @return
     * @throws MQClientException
     */
//    @Bean(name = "orderMessageConsumer")
    public DefaultMQPushConsumer buildOrderMQPushConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "A || C || E");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerOrderly() {

            private AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                MessageExt message = msgs.get(0);
                MessageQueue messageQueue = context.getMessageQueue();
                try {
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New Message: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                this.consumeTimes.incrementAndGet();
                if ((this.consumeTimes.get() % 2) == 0) {
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if ((this.consumeTimes.get() % 3) == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;

            }
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }

    /**
     * 创建一个消费广播消息消费者
     * @return
     * @throws MQClientException
     */
//    @Bean(name = "broadcastMessageConsumer")
    public DefaultMQPushConsumer buildBroadcastMQPushConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "A || C || E");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //set to broadcast mode
        consumer.setMessageModel(MessageModel.BROADCASTING);

        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(message -> {
                try {
                    MessageQueue messageQueue = context.getMessageQueue();
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New Message: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("exception", e);
                }
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }

    /**
     * 创建一个消费定时消息消费者
     * @return
     * @throws MQClientException
     */
//    @Bean(name = "scheduledMessageConsumer")
    public DefaultMQPushConsumer buildScheduledMQPushConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(message -> {
                try {
                    MessageQueue messageQueue = context.getMessageQueue();
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New Message: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("exception", e);
                }
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }

    /**
     * 创建一个批量消息消费者
     * @return
     * @throws MQClientException
     */
//    @Bean(name = "batchMessageConsumer")
    public DefaultMQPushConsumer buildBatchMQPushConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(message -> {
                try {
                    MessageQueue messageQueue = context.getMessageQueue();
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New Message: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("exception", e);
                }
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }

    /**
     * 创建一个筛选消息消费者
     * @return
     * @throws MQClientException
     */
//    @Bean(name = "filterMessageConsumer")
    public DefaultMQPushConsumer buildFilterMQPushConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), MessageSelector.bySql("a between 0 and 3"));
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(message -> {
                try {
                    MessageQueue messageQueue = context.getMessageQueue();
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New Message: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("exception", e);
                }
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }


    /**
     * 创建一个事务消息消费者
     * @return
     * @throws MQClientException
     */
    @Bean(name = "transactionMessageConsumer")
    public DefaultMQPushConsumer buildTransactionMQPushConsumer() throws MQClientException {
        //Instantiate with a producer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(env.getProperty("app.mq.default-consumer-name"));
        // Specify name server addresses.
        consumer.setNamesrvAddr(env.getProperty("app.mq.url"));
        // Subscribe one more more topics to consume.
        consumer.subscribe(env.getProperty("app.message.user.topic"), "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(message -> {
                try {
                    MessageQueue messageQueue = context.getMessageQueue();
                    logger.info("From brokerName [{}], QueueId [{}], Topic[{}], Tags[{}], Keys[{}], Receive New MessageID: {}", messageQueue.getBrokerName(), messageQueue.getQueueId(),
                        message.getTopic(), message.getTags(), message.getKeys(), message.getMsgId());
                    if (logger.isDebugEnabled()) {
                        logger.debug("Message content: {}",  new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                    }
                } catch (UnsupportedEncodingException e) {
                    logger.warn("exception", e);
                }
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //Launch the consumer instance.
        consumer.start();
        return consumer;
    }


}
