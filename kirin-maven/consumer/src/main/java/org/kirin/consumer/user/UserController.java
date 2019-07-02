package org.kirin.consumer.user;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.consumer.message.MessageProduceService;
import org.kirin.consumer.message.body.EventMessageInfo;
import org.kirin.facade.AccountServiceFacade;
import org.kirin.facade.request.UserInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version UserController: UserController 2019-05-13 18:09 All rights reserved.$
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Reference(version = "${kirin.service.version}", group = "${kirin.service.group}")
    private AccountServiceFacade accountServiceFacade;

    @Autowired
    private UserService userService;

    @Resource
    private MessageProduceService appMessageService;

    @GetMapping("")
    @ApiOperation("搜索用户列表")
    public BaseResponse search(String key) {
        logger.info("检索用户: {}", key);
        return ResponseUtil.successResponse(userService.searchUsersByName(key));
    }

    @PostMapping("")
    @ApiOperation("创建用户")
    public BaseResponse create(@RequestBody @Valid UserInfoReq request) {
        logger.info("创建用户: {}", JSON.toJSONString(request));
        Long userId = userService.createUser(request);
        if (userId > 0) {
            EventMessageInfo eventMessage = new EventMessageInfo();
            Map<String, String> data = new HashMap<>(4);
            data.put("userId", String.valueOf(userId));
            data.put("userName", request.getName());
            eventMessage.setData(data);
            appMessageService.sendNormalEventMessage(eventMessage);
        }
        return ResponseUtil.successResponse(userId);
    }

}
