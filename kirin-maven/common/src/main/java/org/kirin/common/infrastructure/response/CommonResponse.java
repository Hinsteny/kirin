package org.kirin.common.infrastructure.response;

/**
 * @author Hinsteny
 * @version $ID: CommonResponse 2019-03-20 16:32 All rights reserved.$
 */
public class CommonResponse <T> extends BaseResponse {

    private T result;

    public CommonResponse() {
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
