package com.binding.data.databinding.chap16;

import com.binding.data.databinding.chap15.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

public class EventFormatter implements Formatter<Event> {

    @Override
    public Event parse(String source, Locale locale) throws ParseException {
        return new Event(Integer.parseInt(source));
    }

    @Override
    public String print(Event event, Locale locale) {
        return event.getId().toString();
    }
}
