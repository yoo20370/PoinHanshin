package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.domain.board.CommentDto;
import com.project.poinhanshin.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments") // /comments?bno=1080 GET
    public List<CommentDto> list(Integer bno) throws Exception {
        return service.getList(bno);
    }

    //댓글을 등록하는 메소드
    @PostMapping("/comments") // /comments?bno=1085 POST
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno) {
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        try {
            service.write(dto);
            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글을 수정하는 메소드
    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto) {

        dto.setCno(cno);

        try {
            service.modify(dto);
            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }

    }

    //지정된 댓글을 삭제하는 메소드
    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno) {
        try {
            int rowCnt = service.remove(cno, bno, "qwer");
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}
//
//        {
//        "pcno":0, "comment":"hihihi", "commenter":"asdf"
//        }
//        수정할때 JSON