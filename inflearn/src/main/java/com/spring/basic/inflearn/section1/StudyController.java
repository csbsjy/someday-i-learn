package com.spring.basic.inflearn.section1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudyController {

    private final StudyService studyService;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/study")
    public ResponseEntity<Void> study(@RequestBody StudyRequestDto studyRequestDto) {
        studyService.study(studyRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
