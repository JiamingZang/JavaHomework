package com.example.redis.controller;

import com.example.redis.common.ResultData;
import com.example.redis.bean.User;
import com.example.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.spi.DirStateFactory.Result;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResultData login(@RequestBody User user){
        User result = service.login(user);
        if (result!=null) {
            return ResultData.success(result);
        }else{
            return ResultData.fail(500, "登录失败");
        }
    }


    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResultData register(@RequestBody User user){
        if (service.register(user)==null) {
            return ResultData.fail(400,"注册失败");
        }else{
            return ResultData.success("");
        }
        
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ResultData getAllUsers(){
        return ResultData.success(service.getAllUsers());
    }
}
