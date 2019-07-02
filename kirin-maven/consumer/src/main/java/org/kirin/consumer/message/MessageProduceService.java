package org.kirin.consumer.message;

import com.alibaba.fastjson.JSON;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.kirin.consumer.message.body.EventMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Hinsteny
 * @version MessageProduceService: MessageProduceService 2019-05-13 17:28 All rights reserved.$
 */
@Component
public class MessageProduceService implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProduceService.class);

    @Resource(name = "environment")
    private Environment env;

    @Resource
    private MQProducer mqProducer;

    private String DEFAULT_TOPIC;

    private String DEFAULT_TAGS;

    private boolean CANUSEDEFAULT;

    private int BATCHSIZE = 10;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.DEFAULT_TOPIC = env.getProperty("app.message.user.topic");
        this.DEFAULT_TAGS = env.getProperty("app.message.user.tags");
        CANUSEDEFAULT = !(StringUtils.isBlank(this.DEFAULT_TOPIC) || StringUtils.isBlank(this.DEFAULT_TAGS));
        String batchSize = env.getProperty("app.message.batch-size");
        if (StringUtils.isNotBlank(batchSize)) {
            this.BATCHSIZE = Integer.valueOf(batchSize);
        }
    }

    /**
     * 发送一个普通消息
     * @param messageInfo 消息内容
     * @return
     */
    public Optional<SendResult> sendNormalEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String message = JSON.toJSONString(messageInfo.getData());
            Message msg = new Message(topic, tags, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = mqProducer.send(msg);
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendNormalEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendNormalEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 发送一批顺序消息
     * @param messageInfo  消息内容
     * @return
     */
    public Optional<SendResult> sendOrderEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String[] tagsList = tags.split(",");
            String messageStr = JSON.toJSONString(messageInfo.getData());
            SendResult sendResult = null;
            for (int i = 0; i < BATCHSIZE; i++) {
                int orderId = i % 10;
                //Create a message instance, specifying topic, tag and message body.
                Message message = new Message(topic, tagsList[i % tagsList.length], "KEY" + i,
                    (i + messageStr).getBytes(RemotingHelper.DEFAULT_CHARSET));
                sendResult = mqProducer.send(message, (mqs, msg, arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }, orderId);
            }
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendOrderEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendOrderEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 发送一条广播消息
     * @param messageInfo  消息内容
     * @return
     */
    public Optional<SendResult> sendBroadcastingEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String messageStr = JSON.toJSONString(messageInfo.getData());
            SendResult sendResult = null;
            for (int i = 0; i < BATCHSIZE; i++) {
                //Create a message instance, specifying topic, tag and message body.
                Message message = new Message(topic, tags, (i + messageStr).getBytes(RemotingHelper.DEFAULT_CHARSET));
                sendResult = mqProducer.send(message);
            }
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendBroadcastingEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendBroadcastingEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 发送一条定时消息
     * @param messageInfo  消息内容
     * @return
     */
    public Optional<SendResult> sendScheduledEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String messageStr = JSON.toJSONString(messageInfo.getData());
            SendResult sendResult = null;
            for (int i = 0; i < BATCHSIZE; i++) {
                //Create a message instance, specifying topic, tag and message body.
                Message message = new Message(topic, tags, (i + messageStr).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // This message will be delivered to consumer 1 minutes later.
                message.setDelayTimeLevel(5);
                sendResult = mqProducer.send(message);
            }
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendScheduledEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendScheduledEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 发送一批打包消息
     * @param messageInfo  消息内容
     * @return
     */
    public Optional<SendResult> sendBatchEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String messageStr = JSON.toJSONString(messageInfo.getData());
            List<Message> messages = new ArrayList<>();
            for (int i = 0; i < BATCHSIZE; i++) {
                //Create a message instance, specifying topic, tag and message body.
                messages.add(new Message(topic, tags, i + "key", (i + messageStr).getBytes(RemotingHelper.DEFAULT_CHARSET)));
            }
            SendResult sendResult = mqProducer.send(messages);
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendBatchEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendBatchEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 发送包含属性集的消息
     * @param messageInfo  消息内容
     * @return
     */
    public Optional<SendResult> sendFilterEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String messageStr = JSON.toJSONString(messageInfo.getData());
            Message messages = new Message(topic, tags, messageStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
            if (MapUtils.isNotEmpty(messageInfo.getProperties())) {
                messageInfo.getProperties().forEach((Object key, Object value) -> messages.putUserProperty((String)key, (String)value));
            }
            SendResult sendResult = mqProducer.send(messages);
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendFilterEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.warn("sendFilterEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 发送事务消息
     * @param messageInfo  消息内容
     * @return
     */
    public Optional<SendResult> sendTransactionEventMessage(EventMessageInfo messageInfo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("创建MQ消息: {}", JSON.toJSONString(messageInfo));
        }
        Optional<SendResult> result = Optional.empty();
        try {
            validateMessageInfo(messageInfo);
            String topic = StringUtils.isNoneBlank(messageInfo.getTopic()) ? messageInfo.getTopic() : DEFAULT_TOPIC;
            String tags = StringUtils.isNoneBlank(messageInfo.getTags()) ? messageInfo.getTags() : DEFAULT_TAGS;
            String messageStr = JSON.toJSONString(messageInfo.getData());
            Message messages = new Message(topic, tags, messageStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = mqProducer.sendMessageInTransaction(messages, null);
            result = Optional.of(sendResult);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("sendTransactionEventMessage IllegalArgumentException", e);
        } catch (UnsupportedEncodingException | MQClientException e) {
            LOGGER.warn("sendTransactionEventMessage exception", e);
        }
        LOGGER.info("发送消息, 结果: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 信息校验
     * @param messageInfo 所要发送的消息对象
     * @throws IllegalArgumentException
     */
    private void validateMessageInfo(EventMessageInfo messageInfo) throws IllegalArgumentException{
        Assert.notNull(messageInfo, "发送消息请求参数不能为空");
        Assert.notNull(messageInfo.getData(), "发送消息请求体不能为空");
        if (!CANUSEDEFAULT) {
            Assert.isTrue(StringUtils.isBlank(messageInfo.getTopic()), "发送消息的topic不能为空");
            Assert.isTrue(StringUtils.isBlank(messageInfo.getTags()), "发送消息的tags不能为空");
        }
    }

}
