package com.project.poinhanshin.mapper.login;


import com.project.poinhanshin.domain.member.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    void save(User user);
    User findByLoginId(String loginId);
    User login(@Param("loginId") String loginId, @Param("password") String password);
}