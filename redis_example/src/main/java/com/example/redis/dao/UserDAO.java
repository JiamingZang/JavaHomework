package com.example.redis.dao;

import com.example.redis.bean.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDAO {
    @Select("select * from user where username = #{username} and password = #{password}")
    public User login(String username, String password);

    @Select("select * from user where id = #{id}")
    public User getUserById(int id);

    @Insert("insert into user (username,password) values (#{username},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void register(User user);

    @Select("select * from user")
    public List<Map<String, Object>> getAllUsers();
}
