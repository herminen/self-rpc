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

    private T gnvokeResult;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public T getGnvokeResult() {
        return gnvokeResult;
    }

    public void setGnvokeResult(T gnvokeResult) {
        this.gnvokeResult = gnvokeResult;
    }
}
