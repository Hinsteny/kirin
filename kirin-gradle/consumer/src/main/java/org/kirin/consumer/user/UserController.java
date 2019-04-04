package org.kirin.consumer.user;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.response.StatusResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.facade.UserServiceFacade;
import org.kirin.facade.request.UserInfoReq;
import org.kirin.message.AppMessageProduceService;
import org.kirin.message.body.UserCreateEventMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user http-service interface
 * @author Hinsteny
 * @version $ID: UserController 2019-03-27 16:13 All rights reserved.$
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Reference(version = "${kirin.service.version}", group = "${kirin.service.group}")
    private UserServiceFacade userServiceFacade;

    @Resource
    private AppMessageProduceService appMessageService;

    @GetMapping("")
    @ApiOperation("搜索用户列表")
    public BaseResponse search(String key) {
        logger.info("检索用户: {}", key);
        return userServiceFacade.search(key);
    }

    @PostMapping("")
    @ApiOperation("创建用户")
    public BaseResponse create(@RequestBody @Valid UserInfoReq request) {
        logger.info("创建用户: {}", JSON.toJSONString(request));
        StatusResponse statusResponse = userServiceFacade.create(request);
        if (statusResponse.isSuccess()) {
            UserCreateEventMessage eventMessage = new UserCreateEventMessage();
            eventMessage.setCreateTime(LocalDateTime.now());
            eventMessage.setUserName(request.getName());
            appMessageService.sendUserCreateEventMessage(eventMessage);
        }
        return ResponseUtil.successResponse(statusResponse.getResult());
    }

}
