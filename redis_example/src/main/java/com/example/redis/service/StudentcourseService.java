package com.example.redis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.redis.bean.Studentcourse;
import com.example.redis.dao.CourseDAO;
import com.example.redis.dao.StudentcourseDAO;
import com.example.redis.dao.UserDAO;

@Service
public class StudentcourseService {
    @Resource
    private StudentcourseDAO dao;
    @Resource
    private UserDAO userdao;
    @Resource
    private CourseDAO coursedao;

    public List<Map<String, Object>> getAllStudentcourses(){
        List<Studentcourse> lsc = dao.getAllStudentcourses();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Studentcourse sc : lsc) {
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", sc.getId());
            temp.put("cid", sc.getCid());
            temp.put("sid", sc.getSid());
            result.add(temp);
        }
        return result;
    }

    @Cacheable("studentsOfCourse")
    public List<Map<String, Object>> getAllStudentsByCid(int cid){
        List<Map<String, Object>> temp = getAllStudentcourses();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String,Object> sc : temp) {
            if ((int)sc.get("cid")==cid) {   
                Map<String,Object> u = JSON.parseObject(JSON.toJSONString(userdao.getUserById((int)sc.get("sid"))));
                u.put("id", u.get("id").toString());
                result.add(u);
            }
        }
        return result;
    }

    @Cacheable("coursesOfStudent")
    public List<Map<String, Object>> getAllCoursesBySid(int sid){
        List<Map<String, Object>> temp = getAllStudentcourses();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String,Object> sc : temp) {
            if ((int)sc.get("sid")==sid) {   
                Map<String,Object> c = JSON.parseObject(JSON.toJSONString(coursedao.getCourseById((int)sc.get("cid"))));
                c.put("id", c.get("id").toString());
                result.add(c);
            }
        }
        return result;
    }

    // @CachePut(cacheNames = "studentcourses")
    @Caching(
        evict={
            @CacheEvict(cacheNames = "studentsOfCourse",allEntries = true),
            @CacheEvict(cacheNames = "coursesOfStudent",allEntries = true)
        }
    )
    public Map<String, Object> addStudentcourse(Studentcourse studentcourse){
        dao.addStudentcourse(studentcourse);
        Map<String, Object> result = JSON.parseObject(JSON.toJSONString(dao.getStudentcourseById(studentcourse.getId())));
        
        return result;
    
    }
    @Caching(
        // put={
        //     @CachePut(cacheNames = "studentOfCourses",key="studentOfCourses.sod")
        // }
        evict={
            @CacheEvict(cacheNames = "studentsOfCourse",key="#studentcourse.cid"),
            @CacheEvict(cacheNames = "coursesOfStudent",key="#studentcourse.sid")
        }
    )
    public void deleteStudentcourse(Studentcourse studentcourse){
        dao.removeStudentcourse(studentcourse.getCid(),studentcourse.getSid());
    }
}
