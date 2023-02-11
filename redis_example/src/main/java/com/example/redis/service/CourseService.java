package com.example.redis.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis.dao.CourseDAO;

@Service
public class CourseService {
    @Resource
    private CourseDAO dao;

    @Cacheable("courses")
    public List<Map<String, Object>> getAllCourses(){
        List<Map<String, Object>> result = dao.getAllCourses();
        for (Map<String,Object> course : result) {
            course.put("id", course.get("id").toString());
        }
        return result;
    }
}
