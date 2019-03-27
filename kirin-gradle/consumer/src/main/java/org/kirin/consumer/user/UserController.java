package org.kirin.consumer.user;

import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.facade.UserServiceFacade;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Reference(version = "${kirin.service.version}", url = "${kirin.service.url}")
    private UserServiceFacade userServiceFacade;

    @GetMapping("")
    @ApiOperation("搜索用户列表")
    public BaseResponse search(String key) {
        logger.info("检索用户: {}", key);
        return userServiceFacade.search(key);
    }

}
