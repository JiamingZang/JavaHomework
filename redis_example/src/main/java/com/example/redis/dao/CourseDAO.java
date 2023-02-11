package com.example.redis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.redis.bean.Course;

@Mapper
public interface CourseDAO {
    @Select("select * from course")
    public List<Map<String, Object>> getAllCourses();

    @Select("select * from course where id = #{id}")
    public Course getCourseById(int id);
}
