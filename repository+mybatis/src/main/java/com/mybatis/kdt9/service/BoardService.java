package com.mybatis.kdt9.service;

import com.mybatis.kdt9.domain.Board;
import com.mybatis.kdt9.domain.BoardEntity;
import com.mybatis.kdt9.dto.BoardDTO;
import com.mybatis.kdt9.mapper.BoardMapper;
import com.mybatis.kdt9.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    BoardRepository boardRepository;
    // 1. 전체 조회 List<BoardDTO>
    public List<BoardDTO> getBoardAll(){
//        List<Board> result = boardMapper.getBoardAll();
        List<BoardEntity> result = boardRepository.findAll();
        List<BoardDTO> list = new ArrayList<>();

        // DateTime -> String
        // SimpleDateFormat(형식).format(dateTime데이터)
        for ( BoardEntity board : result ) {
//      for ( Board board : result ) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .registered(new SimpleDateFormat("yyyy-mm-dd").format(board.getRegistered()))
//                    .registered(board.getRegistered())
                    // dto에는 string, entity datetime
                    .no(board.getWriter() + board.getId())
                    .build();
            list.add(boardDTO);
        }
        return list;
    }

    public void insertBoard(Board board) {
        BoardEntity boardEntity = BoardEntity.builder()
                .title(board.getTitle())
                .writer(board.getWriter())
                .content(board.getContent()).build();
        boardRepository.save(boardEntity);
//        boardMapper.insertBoard(board);
    }

    // patch를 할 때 id랑 정보랑 따로 전달받잖아요?
    public void patchBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getId())
                .orElseThrow(()-> new RuntimeException("board patch : id is wrong"));
        BoardEntity modified = BoardEntity.builder()
                .id(boardEntity.getId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .registered(boardEntity.getRegistered())
                // null을 허용하지 않은 데이터는 값을 무조건 넣어줘야 한다. insert / update
                .build();
        boardRepository.save(modified);
        // save() : insert 할 때
        // save() : 새로운 entity를 insert 할 떄 or 기존 entity를 update 할 때 사용된다.
        // 기본값 ( pk ) 의 상태에 따라 다르게 동작
        // - pk 값이 존재하는 경우  : pk와 연결된 entity update
        // - pk 값이 존재하지 않는 경우 : 새로운 entity를 insert
//        boardMapper.patchBoard(boardDTO);
        // mapper 파일에서는 여러 개를 parameter로 받을 수 있는데
        // xml 파일은 하나밖에 못 받는다.
    }
    public void deleteBoard(int id) {
        // repository의 delete 기본 메소드를 이용. delete(Entity)
        // 1) id를 이용해서 entity를 검색
        // 2) 검색된 entity를 delete에 보내야 삭제 완료
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("board delete : id is wrong"));
        boardRepository.delete(boardEntity);
//        boardMapper.deleteBoard(id);
    }
    public int searchBoard(String word) {
        // select * from board ; List<Board>
        // 검색어가 있으면 title 일치 검색, 없으면 전체 검색
        // List<Board> result = boardMapper.searchBoard(word);
        List<BoardEntity> result = boardRepository.getBoardByWord(word);
        return result.size();
    }
}
