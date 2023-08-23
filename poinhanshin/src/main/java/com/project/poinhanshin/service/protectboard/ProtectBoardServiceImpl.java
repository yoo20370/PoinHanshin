package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtectBoardServiceImpl implements ProtectBoardService{

    ProtectBoardMapper protectBoardMapper;

    // 의존성 주입
    public ProtectBoardServiceImpl(ProtectBoardMapper protectBoardMapper) {
        this.protectBoardMapper = protectBoardMapper;
    }

    // 모든 게시물 리스트를 가져온다.
    @Override
    public List<ProtectBoardDto> bringBoardList(SearchCondition1 sc) {
        System.out.println("실행됨1");
        return protectBoardMapper.selectContentAll(sc);
    }

    @Override
    public List<ProtectBoardDto> bringanimalFilterList(SearchCondition1 sc) {
        return protectBoardMapper.animalFilterList(sc);
    }

    // 게시물 하나만 가져온다.
    @Override
    public ProtectBoardDto bringBoardOne(Integer protectboardno) {
        return protectBoardMapper.selectContentOne(protectboardno);
    }

    // 모든 게시물의 개수를 구한다.
    @Override
    public int countListAll() {
        return protectBoardMapper.countAll();
    }

    // 게시물을 데이터베이스에 저장한다.
    @Override
    public int insertProductBoard(ProtectBoardDto protectBoardDto) {
        return protectBoardMapper.insertContent(protectBoardDto);
    }

    @Override
    public int updateProductBoard(ProtectBoardDto protectBoardDto, Integer LoginId) {
        protectBoardDto.setProtectboard_userno(LoginId);
        return protectBoardMapper.updateContent(protectBoardDto);
    }

    @Override
    public int deleteProductBoard(Integer bno, Integer LoginId) {
        return protectBoardMapper.deleteContent(bno ,LoginId);
    }

    @Override
    public int readWritedBoardno(Integer protectboard_userno) {
        return protectBoardMapper.selectRecentBoardno(protectboard_userno);
    }


    @Override
    public int test() {
        // 게시물 전체 개수 테스트 O
        System.out.println(protectBoardMapper.countAll());

        // selectContentALL 테스트 O
        SearchCondition1 sc = new SearchCondition1();
        List<ProtectBoardDto> list = protectBoardMapper.selectContentAll(sc);
        System.out.println(list.toString());

        // selectContentAll 테스트 O
        System.out.println(protectBoardMapper.selectContentOne(1));

        // insertContent 테스트 O
        byte[] array = null;
        //ProtectBoardDto protectBoardDto = new ProtectBoardDto(1, "공고 테스트" , array , "강아지" , true , true );
        //protectBoardMapper.insertContent(protectBoardDto);

        // updateContent 테스트 O
        //ProtectBoardDto protectBoardDto2 = new ProtectBoardDto(1, "공고 테스트 수정" , array , "강아지" , false , false );
        //protectBoardDto2.setProtectboardno(6);
        //protectBoardMapper.updateContent(protectBoardDto2);

        // deleteContent 테스트 X


        return 1;
    }

}

