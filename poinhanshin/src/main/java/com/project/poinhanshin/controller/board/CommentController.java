package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.domain.board.CommentDto;
import com.project.poinhanshin.service.board.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 읽어오기
    @GetMapping("/comments")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> readCommentList( Integer boardcomment_boardno ){
        // 댓글 불러오기
        List<CommentDto> list = commentService.getCommentList(boardcomment_boardno);
        // 댓글 반환
        return new ResponseEntity<List<CommentDto>>(list , HttpStatus.OK);
    }
    // 댓글 추가
    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto){
        Integer loginid = 1;
        commentDto.setBoardcomment_userno(loginid);
        System.out.println("등록 : "+commentDto);


        if(commentService.addComment(commentDto) != 1){
            return new ResponseEntity<String>("WRITE_ERROR" , HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("WRITE_OK" , HttpStatus.OK);
        }
    }
    // 댓글 수정
    @PatchMapping("/comments")
    @ResponseBody
    public ResponseEntity<String> modifyComment(@RequestBody CommentDto commentDto , Integer loginId ){
        commentDto.setBoardcomment_userno(loginId);

        if(commentService.modifyComment(commentDto) != 1){
            return new ResponseEntity<String>("MODIFY_ERROR" ,HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("MODIFY_OK" , HttpStatus.OK);
        }

    }


    // 댓글 삭제
    @DeleteMapping("/comments/{commentno}")
    @ResponseBody
    public ResponseEntity<String> removeComment(@PathVariable Integer commentno , Integer boardcomment_boardno ){

        System.out.println(commentno);
        System.out.println(boardcomment_boardno);
        // 임시 로그인
        Integer loginId = 1;

        if(commentService.removeComment(boardcomment_boardno , commentno , loginId) != 1){
            return new ResponseEntity<String>("REMOVE_ERROR" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("REMOVE_OK" , HttpStatus.OK);
    }


}
