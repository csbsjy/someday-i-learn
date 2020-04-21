package com.study.mocktest.service;

import com.study.mocktest.domain.ArticleRepository;
import com.study.mocktest.dto.ArticleUpdateRequestDto;
import com.study.mocktest.session.UserSessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
    private final UserSessionManager sessionManager;

    public void write(ArticleUpdateRequestDto articleUpdateRequestDto) {
        articleRepository.save(articleUpdateRequestDto.toEntity(sessionManager.extractUser().getUserId()));
    }
}
