package com.project.poinhanshin.repository;


import com.project.poinhanshin.domain.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    int count(Integer bno);
    int deleteAll(Integer bno);
    int delete(@Param("cno") Integer cno, @Param("commenter") String commenter);
    int insert(CommentDto dto);
    List<CommentDto> selectAll(Integer bno);
    CommentDto select(Integer cno);
    int update(CommentDto dto);
}
