package com.example.redis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.KafkaMessageProducer;
import com.example.redis.bean.Studentcourse;
import com.example.redis.common.ResultData;
import com.example.redis.service.StudentcourseService;

@RestController
@RequestMapping("/studentcourse")
public class StudentcourseController {
    @Autowired
    private StudentcourseService service;
    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @RequestMapping(value="/getAllStudentsByCid",method = RequestMethod.GET)
    public ResultData getAllStudentsByCid(int cid){
        return ResultData.success(service.getAllStudentsByCid(cid));
    }

    @RequestMapping(value="/getAllCoursesBySid",method = RequestMethod.GET)
    public ResultData getAllCoursesBySid(int sid){
        return ResultData.success(service.getAllCoursesBySid(sid));
    }

    @RequestMapping(value="/addStudentcourse",method = RequestMethod.POST)
    public ResultData addStudentcourse(@RequestBody Studentcourse studentcourse){
        kafkaMessageProducer.send("学生"+studentcourse.sid+"注册课程"+studentcourse.cid);
        return ResultData.success(service.addStudentcourse(studentcourse));
    }

    @RequestMapping(value="/deleteStudentcourse",method = RequestMethod.POST)
    public ResultData deleteStudentcourse(@RequestBody Studentcourse studentcourse){
        kafkaMessageProducer.send("学生"+studentcourse.sid+"退出课程"+studentcourse.cid);
        service.deleteStudentcourse(studentcourse);
        return ResultData.success("");
    }
}
