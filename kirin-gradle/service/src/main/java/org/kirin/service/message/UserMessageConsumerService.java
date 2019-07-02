package org.kirin.service.message;

import com.alibaba.fastjson.JSON;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.core.env.Environment;

/**
 * @author Hinsteny
 * @version $ID: AppMessageService 2019-04-02 10:34 All rights reserved.$
 */
public class UserMessageConsumerService implements MessageListenerConcurrently {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource(name = "environment")
    private Environment env;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
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
    }
}
