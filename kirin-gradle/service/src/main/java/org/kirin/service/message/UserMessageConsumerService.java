package org.kirin.service.message;

import com.alibaba.fastjson.JSON;
import java.util.List;
import javax.annotation.Resource;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * @author Hinsteny
 * @version $ID: AppMessageService 2019-04-02 10:34 All rights reserved.$
 */
public class UserMessageConsumerService implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMessageConsumerService.class);

    @Resource(name = "environment")
    private Environment env;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        LOGGER.info("{}: Queue: {} ,  Receive New Messages: {}", Thread.currentThread().getName(), JSON.toJSONString(consumeConcurrentlyContext.getMessageQueue()), JSON.toJSONString(msgs));
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
