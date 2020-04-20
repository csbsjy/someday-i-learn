package com.study.mocktest.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String subject;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public Article(String userId, String subject, String contents) {
        this.userId = userId;
        this.subject = subject;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public void updateArticle(String subject, String contents) {
        this.subject = subject;
        this.contents = contents;
        this.updateDate = LocalDateTime.now();
    }

}
