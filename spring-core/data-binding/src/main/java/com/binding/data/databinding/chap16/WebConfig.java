package com.binding.data.databinding.chap16;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // 모든 Controller 에서 동작함
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventConverter.StringToEventConverter());
        registry.addConverter(new EventConverter.EventToStringConverter());

        registry.addFormatter(new EventFormatter());
    }
}
