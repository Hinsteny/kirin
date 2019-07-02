package org.kirin.common.infrastructure.util;

import org.kirin.common.infrastructure.enums.ApiResultCodeType;
import org.kirin.common.infrastructure.response.CommonResponse;
import org.kirin.common.infrastructure.response.StatusResponse;

/**
 * @author Hinsteny
 * @version $ID: ResponseUtil 2019-03-20 16:30 All rights reserved.$
 */
public abstract class ResponseUtil {

    public static StatusResponse status(boolean status) {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setSuccess(true);
        statusResponse.setResult(status);
        return statusResponse;
    }

    public static StatusResponse successStatus() {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setSuccess(true);
        statusResponse.setResult(true);
        return statusResponse;
    }

    public static StatusResponse failureStatus() {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setSuccess(false);
        return statusResponse;
    }

    public static StatusResponse failureStatus(ApiResultCodeType resultCodeType) {
        return failureStatus(resultCodeType, resultCodeType.getMessage());
    }

    public static StatusResponse failureStatus(ApiResultCodeType resultCodeType, String message) {
        StatusResponse statusResponse = failureStatus();
        statusResponse.setResultCode(resultCodeType.getCode());
        statusResponse.setResultDesc(message);
        return statusResponse;
    }

    public static <T> CommonResponse<T> successResponse(T result) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setResult(result);
        return response;
    }

    public static <T> CommonResponse<T> failureResponse(ApiResultCodeType resultCodeType) {
        return failureResponse(resultCodeType, resultCodeType.getMessage());
    }

    public static <T> CommonResponse<T> failureResponse(ApiResultCodeType resultCodeType, String message) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setResultCode(resultCodeType.getCode());
        response.setResultDesc(message);
        return response;
    }
}
