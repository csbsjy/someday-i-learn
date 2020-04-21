package com.resource_validation.resource_validation.validation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Event {

    Integer id;

    @NotEmpty
    String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
