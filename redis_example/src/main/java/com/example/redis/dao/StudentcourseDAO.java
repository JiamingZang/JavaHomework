package com.example.redis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.redis.bean.Studentcourse;

@Mapper
public interface StudentcourseDAO {
    @Select("select * from studentcourse")
    public List<Studentcourse> getAllStudentcourses();

    @Select("select * from studentcourse where cid = #{cid}")
    public List<Studentcourse> getAllStudentcoursesByCid(int cid);

    @Select("select * from studentcourse where sid = #{sid}")
    public List<Studentcourse> getAllStudentcoursesBySid(int sid);

    @Insert("insert into studentcourse (cid,sid) values (#{cid},#{sid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void addStudentcourse(Studentcourse studentcourse);

    @Select("select * from studentcourse where id = #{id}")
    public Studentcourse getStudentcourseById(int id);

    @Delete("delete from studentcourse where cid = #{cid} and sid = #{sid}")
    public void removeStudentcourse(int cid, int sid);
}
