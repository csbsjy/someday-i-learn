package com.springcore.iocdi.chap6_7;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test") // test에서 쓸 Repository 이다.
public class TestRepository {
}
