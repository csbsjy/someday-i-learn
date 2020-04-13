package com.springcore.iocdi.chap3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository @Primary // 이 Bean을 우선적으로 받겠다.
public class JaeyeonBookRepository implements BookRepository{
}
