package org.kirin.service.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kirin.common.infrastructure.response.BaseResponse;
import org.kirin.common.infrastructure.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version ConfigController: ConfigController 2019-05-14 10:47 All rights reserved.$
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @NacosValue(value = "${appName}", autoRefreshed = true)
    private String appName;

    /** ======================== test nacos config  =======================**/
    @PostMapping("/queryAppName")
    @ApiOperation("查询nacos配置项值")
    public BaseResponse queryAppName() {
        logger.info("查询nacos配置项值", JSON.toJSONString(appName));
        return ResponseUtil.successResponse(appName);
    }

}
