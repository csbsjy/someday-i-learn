package com.study.mocktest.api;

import com.study.mocktest.dto.ArticleUpdateRequestDto;
import com.study.mocktest.service.BoardService;
import com.study.mocktest.session.AccessUserSessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final AccessUserSessionManager sessionManager;
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<Void> write(@RequestBody ArticleUpdateRequestDto updateRequestDto) throws IllegalAccessException {
        if (sessionManager.extractUser() == null) {
            throw new IllegalAccessException("로그인하지 않은 사용자");
        }

        boardService.write(updateRequestDto);

        return ResponseEntity.ok(null);
    }
}
