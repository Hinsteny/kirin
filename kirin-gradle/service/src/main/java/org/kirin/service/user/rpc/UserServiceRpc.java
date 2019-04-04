package org.kirin.service.user.rpc;

import java.util.List;
import org.apache.dubbo.config.annotation.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.CommonResponse;
import org.kirin.common.infrastructure.response.StatusResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.kirin.facade.UserServiceFacade;
import org.kirin.facade.request.UserInfoReq;
import org.kirin.facade.response.UserInfoResp;
import org.kirin.service.user.UserService;
import org.kirin.service.user.request.CreateUserReq;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * user http-service interface
 * @author Hinsteny
 * @version $ID: UserController 2019-03-07 16:13 All rights reserved.$
 */
@Service(version = "${kirin.service.version}", group = "${kirin.service.group}", timeout = 3000)
public class UserServiceRpc implements UserServiceFacade {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 检索用户
     *
     * @param key 用户名
     */
    @Override
    public CommonResponse<List<UserInfoResp>> search(String key) {
        logger.info("检索用户: {}", key);
        List<UserInfoResp> userInfoResps = userService.searchUserByName(key);
        return ResponseUtil.successResponse(userInfoResps);
    }

    /**
     * 创建用户
     *
     * @param request 用户信息请求
     */
    @Override
    public StatusResponse create(UserInfoReq request) {
        userService.createUser(CreateUserReq.builder().name(request.getName()).password(request.getPassword()).sex(request.getSex()).email(request.getEmail()).build());
        return ResponseUtil.successStatus();
    }
}
