package com.binding.data.databinding.chap15;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Event event = (Event) getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }
}

// Stateful
// 서로다른 스레드에 공유
// PropertyEditor 의 구현체는 스레드에 공유하면 한된다
// 빈으로 등록해서 쓰면 안된다