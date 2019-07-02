package org.kirin.facade;

import java.util.List;
import org.kirin.common.infrastructure.response.CommonResponse;
import org.kirin.common.infrastructure.response.StatusResponse;
import org.kirin.facade.request.UserInfoReq;
import org.kirin.facade.response.UserInfoResp;

/**
 * User service statement
 * @author Hinsteny
 * @version $ID: UserServiceFacade 2019-03-26 20:15 All rights reserved.$
 */
public interface UserServiceFacade {

    /**
     * 检索用户
     * @param key 用户名
     * @return
     */
    CommonResponse<List<UserInfoResp>> search(String key);

    /**
     * 创建用户
     * @param request 用户信息请求
     * @return
     */
    StatusResponse create(UserInfoReq request);

}
