package com.project.poinhanshin.mapper.member;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
    void updateUser(User user);

    void dismissUser(Long userno);

    List<BoardDto> selectMyBoard(Integer userno);
    List<BoardDto> writeMyBoard(Integer userno);

    // 마이페이지 임보자 게시판 즐겨찾기
    List<ProtectBoardDto> selectMyprotectboard(Integer userno);

    // 마이페이지 내가 쓴 임보자 게시글 불러오기
    List<ProtectBoardDto> writeMyprotectboard(Integer userno);

}
