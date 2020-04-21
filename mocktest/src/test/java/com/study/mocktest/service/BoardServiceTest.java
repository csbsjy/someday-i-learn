package com.study.mocktest.service;

import com.study.mocktest.domain.ArticleRepository;
import com.study.mocktest.dto.ArticleUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class BoardServiceTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    BoardService boardService;

    @DisplayName("글 제목, 글 내용을 작성하면 유저 ID 정보까지 같이 저장된다")
    @Test
    void write() {
        //given
        ArticleUpdateRequestDto requestDto = new ArticleUpdateRequestDto("글 제목", "글 내용");

        //when
        boardService.write(requestDto);

        //then
        assertThat(articleRepository.findByUserId("a1010100z")).isNotNull();
    }
}


