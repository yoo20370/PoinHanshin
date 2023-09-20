package com.project.poinhanshin.mapper.member;

import com.project.poinhanshin.domain.member.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
    void updateUser(User user);

    void dismissUser(Long userno);
}
