package org.kirin.message;

import com.alibaba.fastjson.JSON;
import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.kirin.message.body.UserCreateEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Hinsteny
 * @version $ID: AppMessageService 2019-04-02 10:34 All rights reserved.$
 */
@Component
public class AppMessageProduceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppMessageProduceService.class);

    @Resource(name = "environment")
    private Environment env;

    @Resource(name = "defaultMQProducer")
    private MQProducer mqProducer;

    public void sendUserCreateEventMessage(UserCreateEventMessage userCreateEventMessage) {
        String message = JSON.toJSONString(userCreateEventMessage);
        LOGGER.info("创建MQ消息: {}", message);
        SendResult sendResult = null;
        try {
            Message msg = new Message(env.getProperty("app.message.user.topic"), env.getProperty("app.message.user.tags"), message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //Call send message to deliver message to one of brokers.
            sendResult = mqProducer.send(msg);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendUserCreateEventMessage exception", e);
        }
        LOGGER.info("发送消息: [{}], 结果: {}", message, JSON.toJSONString(sendResult));
    }
}
