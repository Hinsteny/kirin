package org.kirin.infrastructure.response;

import java.io.Serializable;

/**
 * @author Hinsteny
 * @version $ID: BaseResponse 2019-03-20 16:32 All rights reserved.$
 */
public class BaseResponse implements Serializable {

    private boolean success = false;

    private String resultCode;

    private String resultDesc;

    public BaseResponse() {
    }

    public BaseResponse(boolean success, String resultCode, String resultDesc) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
