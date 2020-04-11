package com.springcore.iocdo.chap2;

import com.springcore.iocdo.IoCDiApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = IoCDiApplication.class) // 여기부터 ComponentScan 을 해라
public class ApplicationConfig {

//    @Bean
//    public BookRepository bookRepository(){
//        return new BookRepository();
//    }
//
//    @Bean
//    public BookService bookService(BookRepository bookRepository){
//        BookService bookService = new BookService();
////        bookService.setBookRepository(bookRepository);
//        return bookService;
//    }

}
