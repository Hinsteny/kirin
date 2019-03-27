package org.kirin.common.infrastructure.enums;

/**
 * 统一的返回前端接口结果码定义
 * ==============================
 * 1. 客户端请求非法(参数缺失, 有误等): CXXXX
 *
 * ==============================
 * @author Hinsteny
 * @version $ID: ApiResultCodeType 2019-03-20 16:34 All rights reserved.$
 */
public enum ApiResultCodeType {

    PARAMETER_ILLEGAL("C0000", "参数非法"),

    SERVICE_DEAL_FAILED("S0000", "服务处理失败"),

    SERVICE_EXCEPTION("E0000", "服务器内部错误"),
    ;

    /**
     * 代码
     */
    private final String code;

    /**
     * 信息
     */
    private final String message;

    /**
     * 构造
     */
    ApiResultCodeType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
