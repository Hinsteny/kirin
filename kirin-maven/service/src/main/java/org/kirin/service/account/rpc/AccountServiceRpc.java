package org.kirin.service.account.rpc;

import java.util.List;
import org.apache.dubbo.config.annotation.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.CommonResponse;
import org.kirin.common.infrastructure.response.StatusResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.facade.AccountServiceFacade;
import org.kirin.facade.request.AccountInfoReq;
import org.kirin.facade.response.AccountInfoResp;
import org.kirin.service.account.AccountService;
import org.kirin.service.account.request.CreateAccountReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * account http-service interface
 * @author Hinsteny
 * @version $ID: UserController 2019-03-07 16:13 All rights reserved.$
 */
@Service(version = "${kirin.service.version}", group = "${kirin.service.group}", timeout = 3000)
public class AccountServiceRpc implements AccountServiceFacade {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    /**
     * 检索账户列表
     *
     * @param userId 用户ID
     */
    @Override
    public CommonResponse<List<AccountInfoResp>> queryAccountsByUserId(String userId) {
        logger.info("检索用户账户列表: {}", userId);
        List<AccountInfoResp> accountInfoResps = accountService.searchAccoountsByUserId(userId);
        return ResponseUtil.successResponse(accountInfoResps);
    }

    /**
     * 为用户创建新账户
     *
     * @param request 用户信息请求
     */
    @Override
    public StatusResponse create(AccountInfoReq request) {
        CreateAccountReq user = new CreateAccountReq();
        BeanUtils.copyProperties(request, user);
        accountService.createAccount(user);
        return ResponseUtil.successStatus();
    }

}
