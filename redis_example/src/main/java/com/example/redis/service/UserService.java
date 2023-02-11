package com.example.redis.service;

import com.example.redis.common.ResultData;
import com.example.redis.bean.User;
import com.example.redis.dao.UserDAO;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
@Service
public class UserService {
    @Resource
    private UserDAO dao;

    
    public User login(User user){
        System.out.println(user.getPassword());
        return dao.login(user.getUsername(),user.getPassword());
    }

    public User register(User user){
        dao.register(user);
        User result = dao.getUserById(user.getId());
        
        return result;
    
    }

    @Cacheable("students")
    public List<Map<String, Object>> getAllUsers(){
        List<Map<String, Object>> result = dao.getAllUsers();
        for (Map<String,Object> user : result) {
            user.put("id", user.get("id").toString());
        }
        return result;
    }
}
