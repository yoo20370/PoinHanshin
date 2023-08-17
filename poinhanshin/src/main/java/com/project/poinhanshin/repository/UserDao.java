package com.project.poinhanshin.repository;


import com.project.poinhanshin.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    void save(User user);
    User findByLoginId(String loginId);
    User login(@Param("loginId") String loginId, @Param("password") String password);
}