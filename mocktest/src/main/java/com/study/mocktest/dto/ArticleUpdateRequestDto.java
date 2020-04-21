package com.study.mocktest.dto;

import com.study.mocktest.domain.Article;
import lombok.Getter;

@Getter
public class ArticleUpdateRequestDto {
    private String subject;
    private String contents;

    public ArticleUpdateRequestDto(String subject, String contents) {
        this.subject = subject;
        this.contents = contents;
    }

    public Article toEntity(String userId) {
        return new Article(userId, subject, contents);
    }

}
