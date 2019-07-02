package org.kirin.consumer.message;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.facade.request.UserInfoReq;
import org.kirin.consumer.message.body.EventMessageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version MessageController: MessageController 2019-05-13 17:28 All rights reserved.$
 */
@RestController
@RequestMapping("/msg")
public class MessageControllerForTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private MessageProduceService messageService;

    @PostMapping("/user-create")
    @ApiOperation("发送新用户创建事件消息")
    public BaseResponse createUserMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送新用户创建事件消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        messageService.sendNormalEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/normal")
    @ApiOperation("发送一个普通事件消息")
    public BaseResponse createNormalMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送一个普通事件消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        messageService.sendNormalEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/order")
    @ApiOperation("发送一批顺序事件消息")
    public BaseResponse createOrderMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送一批顺序事件消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        messageService.sendOrderEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/broadcasting")
    @ApiOperation("发送一条广播消息")
    public BaseResponse createBroadcastingMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送一条广播消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        messageService.sendBroadcastingEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/schedule")
    @ApiOperation("发送一条定时消息")
    public BaseResponse createScheduledMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送一条广播消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        messageService.sendScheduledEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/batch")
    @ApiOperation("发送一批打包消息")
    public BaseResponse createBatchMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送一批打包消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        eventMessage.setData(data);
        messageService.sendBatchEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/filter")
    @ApiOperation("发送包含属性用以进行筛选消息")
    public BaseResponse createFilterMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送包含属性用以进行筛选消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        eventMessage.setProperties(request.getProperties());
        messageService.sendFilterEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

    @PostMapping("/transaction")
    @ApiOperation("发送事务消息")
    public BaseResponse createTransactionMessage(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送事务消息: {}", JSON.toJSONString(request));
        EventMessageInfo eventMessage = new EventMessageInfo();
        eventMessage.setTopic(request.getTopic());
        eventMessage.setTags(request.getTags());
        Map<String, String> data = new HashMap<>(4);
        data.put("userId", UUID.randomUUID().toString());
        data.put("userName", request.getName());
        eventMessage.setData(data);
        eventMessage.setProperties(request.getProperties());
        messageService.sendTransactionEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

}