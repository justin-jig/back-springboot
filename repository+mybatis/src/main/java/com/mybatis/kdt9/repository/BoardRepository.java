package com.mybatis.kdt9.repository;

import com.mybatis.kdt9.domain.BoardEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    @Query("select b from BoardEntity b where (b.title=:word or :word = '')")
    // 확인해야 되는 조건 2개 ( title이 일치하는가 or 검색어가 비어있는가 )
    List<BoardEntity> getBoardByWord(@Param("word") String word);
}
