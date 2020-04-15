package com.springcore.iocdi.chap6_7;

import com.springcore.iocdi.chap5.Proto;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {

    @Autowired
    Proto proto;

    @Autowired
    private ObjectProvider<Proto> protos;

    public Proto getProto() {
        return protos.getIfAvailable();
    }
}
