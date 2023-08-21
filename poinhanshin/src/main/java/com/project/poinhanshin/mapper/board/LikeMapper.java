package com.project.poinhanshin.mapper.board;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    int likeCheck(Integer LoginId , Integer bno);

    int addLike(Integer LoginId , Integer bno);

    int deleteLike(Integer LoginId , Integer bno);

    int updateLikeCnt(Integer bno , Integer num);

    //int LikeCntUp(Integer bno);

    //int LikeCntDown(Integer bno);
}
