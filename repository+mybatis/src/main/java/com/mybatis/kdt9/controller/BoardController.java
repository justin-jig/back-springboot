package com.mybatis.kdt9.controller;

import com.mybatis.kdt9.domain.Board;
import com.mybatis.kdt9.dto.BoardDTO;
import com.mybatis.kdt9.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/board")
    @Operation(summary = "문자열 반복", description = "파라미터로 받은 문자열을 2번 반복합니다.")
    //@Parameter(name = "str", description = "2번 반복할 문자열")
    @SecurityRequirement(name = "bearer")
    public String getBoards(Model model){
        List<BoardDTO> list = boardService.getBoardAll();
        model.addAttribute("list", list);
        return "board";
    }
    @GetMapping("/board/search")
    @Operation(
            summary = "Fetch all plants",
            description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @ResponseBody
    public int searchBoard(@RequestParam String word){
        // select - 조건에 따라 다른 결과를 return
        return boardService.searchBoard(word);
    }
    @PostMapping("/board")
    @ResponseBody
    public void insertBoard(@RequestBody Board board){
        //        // 게시글 작성
        boardService.insertBoard(board);
        // 템플릿을 return 해야 하는데 void로 되어 있으면
        // 현재 템플릿을 다시 보여준다.
    }
    @PatchMapping("/board")
    @ResponseBody
    public void patchBoard(@RequestBody BoardDTO boardDTO){
        boardService.patchBoard(boardDTO);
    }
    @DeleteMapping("/board")
    @ResponseBody
    public void deleteBoard(@RequestParam int id){
        boardService.deleteBoard(id);
    }
}
