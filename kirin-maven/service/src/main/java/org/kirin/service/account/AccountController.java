package org.kirin.service.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.service.account.request.CreateAccountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * account http-service interface
 * @author Hinsteny
 * @version $ID: UserController 2019-03-07 16:13 All rights reserved.$
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @NacosValue(value = "${appName}", autoRefreshed = true)
    private String appName;

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    @ApiOperation("搜索用户列表")
    public BaseResponse search(String key) {
        logger.info("检索用户账户: {}", key);
        return ResponseUtil.successResponse(accountService.searchAccoountsByUserId(key));
    }

    @PostMapping("")
    @ApiOperation("创建用户")
    public BaseResponse create(@RequestBody @Valid CreateAccountReq request) {
        logger.info("创建用户: {}", JSON.toJSONString(request));
        Long accountId = accountService.createAccount(request);
        return accountId > 0 ? ResponseUtil.successStatus() : ResponseUtil.failureStatus() ;
    }

}
