package com.liuhai.rpc.transbean;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 21:29
 * Description: No Description
 */
public class RpcResponse <T> {

    private String responseId;

    private T invokeResult;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public T getInvokeResult() {
        return invokeResult;
    }

    public void setInvokeResult(T invokeResult) {
        this.invokeResult = invokeResult;
    }
}
