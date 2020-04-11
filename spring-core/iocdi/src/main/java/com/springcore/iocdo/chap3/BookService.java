package com.springcore.iocdo.chap3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired @Qualifier("jaeyeonBookRepository")
    BookRepository bookRepository;



    // 생성자주입
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired(required = false) // 이게 없어도 정상적으로 생성된다. 대신 bookRepository 는 null이 되겠지
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
