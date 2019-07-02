package org.kirin.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.service.user.request.CreateUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user http-service interface
 * @author Hinsteny
 * @version $ID: UserController 2019-03-07 16:13 All rights reserved.$
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @NacosValue(value = "${appName}", autoRefreshed = true)
    private String appName;

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ApiOperation("搜索用户列表")
    public BaseResponse search(String key) {
        logger.info("检索用户: {}", key);
        return ResponseUtil.successResponse(userService.searchUserByName(key));
    }

    @PostMapping("")
    @ApiOperation("创建用户")
    public BaseResponse create(@RequestBody @Valid CreateUserReq request) {
        logger.info("创建用户: {}", JSON.toJSONString(request));
        userService.createUser(request);
        return ResponseUtil.successResponse(new ArrayList<>());
    }

    /** ======================== test nacos config  =======================**/
    @PostMapping("/queryConfigName")
    @ApiOperation("查询nacos配置项值")
    public BaseResponse queryConfig() {
        logger.info("查询nacos配置项值", JSON.toJSONString(appName));
        return ResponseUtil.successResponse(appName);
    }

}
