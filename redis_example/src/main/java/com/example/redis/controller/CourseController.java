package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.common.ResultData;
import com.example.redis.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService service;
    
    @RequestMapping(value="/",method = RequestMethod.GET)
    public ResultData getAllCourses(){
        return ResultData.success(service.getAllCourses());
    }
}
