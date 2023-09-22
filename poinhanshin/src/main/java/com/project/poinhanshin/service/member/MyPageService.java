package com.project.poinhanshin.service.member;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.mapper.member.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {
    MyPageMapper myPageMapper;
    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }
    public void updateUser(User user) {
        myPageMapper.updateUser(user);
    }
    public void dismiss(Long userno) {
        myPageMapper.dismissUser(userno);
    }

    public List<BoardDto> writeMyBoard(Integer userno) {
        return myPageMapper.writeMyBoard(userno);
    }
}
