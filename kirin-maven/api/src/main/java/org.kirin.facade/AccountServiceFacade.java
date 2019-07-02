package org.kirin.facade;

import java.util.List;
import org.kirin.common.infrastructure.response.CommonResponse;
import org.kirin.common.infrastructure.response.StatusResponse;
import org.kirin.facade.request.AccountInfoReq;
import org.kirin.facade.response.AccountInfoResp;

/**
 * account service statement
 * @author Hinsteny
 * @version $ID: AccountServiceFacade 2019-05-14 20:15 All rights reserved.$
 */
public interface AccountServiceFacade {

    /**
     * 检索账户列表
     * @param userId 用户ID
     * @return
     */
    CommonResponse<List<AccountInfoResp>> queryAccountsByUserId(String userId);

    /**
     * 为用户创建新账户
     * @param request 用户信息请求
     * @return
     */
    StatusResponse create(AccountInfoReq request);

}
