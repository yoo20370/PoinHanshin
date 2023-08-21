package com.project.poinhanshin.service.board;

import com.project.poinhanshin.mapper.board.LikeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    LikeDaoImpl likeDaoImpl;

    @Autowired
    public LikeServiceImpl(LikeDaoImpl likeDaoImpl) {
        this.likeDaoImpl = likeDaoImpl;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String likeFunction(Integer userNo, Integer bno) {
        // 메시지 담을 변수
        String msg = "";

        int check = likeDaoImpl.likeCheck(userNo , bno);
        if(check != 1){
            // likeboard 테이블에 튜플 추가
            likeDaoImpl.addLike(userNo , bno);
            // borad 테이블의 likecount 속성의 값을 1증가시킨다.
            likeDaoImpl.updateLikeCnt(bno , 1);
            msg = "좋아요 버튼을 활성화 합니다.";
        }else{
            // likeboard 테이블에서 튜플 제거
            likeDaoImpl.deleteLike(userNo , bno);
            // board 테이블의 likecount 속성 값을 1 감소시킨다.
            likeDaoImpl.updateLikeCnt(bno,-1);

            msg = "좋아요 버튼을 비활성화 합니다.";
        }
        return msg;
    }

    /*@Transactional(rollbackFor = Exception.class)
    @Override
    public String checkLike(Integer userNo, Integer bno) throws Exception {
        // 좋아요 버튼이 있는지 확인
        int check = likeDaoImpl.likeCheck(userNo , bno);
        if(check == 1)
            throw new Exception("이미 Like 테이블이 존재합니다.");
        // likeboard 테이블에 튜플 추가
        likeDaoImpl.addLike(userNo , bno);
        // borad 테이블의 likecount 속성의 값을 1증가시킨다.
        likeDaoImpl.LikeCntUp(bno);
        return "좋아요 버튼을 활성화합니다..";
    }x

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String nonCheckLike(Integer userNo, Integer bno) throws Exception {
        // 좋아요 버튼이 있는지 확인
        int check = likeDaoImpl.likeCheck(userNo , bno);
        if(check == 1)
            throw new Exception("Like 테이블이 존재하지 않습니다.");
        // likeboard 테이블에서 튜플 제거
        likeDaoImpl.deleteLike(userNo , bno);
        // board 테이블의 likecount 속성 값을 1 감소시킨다.
        likeDaoImpl.LikeCntDown(bno);


        return "좋아요 버튼을 비활성화합니다..";
    }*/



}
