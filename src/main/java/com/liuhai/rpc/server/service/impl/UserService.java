package com.liuhai.rpc.server.service.impl;

import com.liuhai.rpc.server.service.IUserService;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 2020/2/18
 * Time: 21:47
 * Description: No Description
 */
public class UserService implements IUserService {
    @Override
    public void helloWorld() {
        System.out.println("hello world");
    }

    @Override
    public String responseUser(String userName) {
        return "hello " + userName;
    }
}
