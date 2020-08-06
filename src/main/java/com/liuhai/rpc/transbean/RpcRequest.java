package com.liuhai.rpc.transbean;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/17
 * Time: 22:43
 * Description: No Description
 */
public class RpcRequest {

    /**
     * 请求唯一id
     */
    private String requestId;

    /**
     * 服务全限定名
     */
    private String service;

    /**
     * 方法名
     */
    private String method;

    /**
     * 方法参数类型
     */
    private String[] paramType;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String[] getParamType() {
        return paramType;
    }

    public void setParamType(String[] paramType) {
        this.paramType = paramType;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", service='" + service + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
