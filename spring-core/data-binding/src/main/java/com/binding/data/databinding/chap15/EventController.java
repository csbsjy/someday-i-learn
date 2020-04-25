package com.binding.data.databinding.chap15;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @InitBinder
    public void init(WebDataBinder binder){
        binder.registerCustomEditor(Event.class, new EventEditor());
    }

    // TODO: event 에 들어오는 Integer를 Event로 받는다
    @GetMapping("/event/{event}")
    public String getEvent(@PathVariable Event event){
        System.out.println(event);
        return String.valueOf(event.getId());
    }

}
