package com.mybatis.kdt9.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "게시물 리스트 응답DTO")
public class BoardDTO {

    private int id;
    @Schema(description = "사용자 이메일", nullable = false, example = "k12@gmail.com")
    private String title, content, writer, registered;
    private String no;
}
