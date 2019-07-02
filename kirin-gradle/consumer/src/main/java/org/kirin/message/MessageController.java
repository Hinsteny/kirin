package org.kirin.message;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.facade.request.UserInfoReq;
import org.kirin.message.body.UserCreateEventMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version $ID: MessageController 2019-04-15 20:14 All rights reserved.$
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private AppMessageProduceService appMessageService;

    @PostMapping("/user-create")
    @ApiOperation("发送新用户创建事件消息")
    public BaseResponse create(@RequestBody @Valid UserInfoReq request) {
        logger.info("发送新用户创建事件消息: {}", JSON.toJSONString(request));
        UserCreateEventMessage eventMessage = new UserCreateEventMessage();
        eventMessage.setCreateTime(LocalDateTime.now());
        eventMessage.setUserName(request.getName());
        appMessageService.sendUserCreateEventMessage(eventMessage);
        return ResponseUtil.successStatus();
    }

}
