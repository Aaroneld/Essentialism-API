package com.merrifield.Essentialism.API.models.ProjectModels;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ProjectMinimum {
    @NotNull
    private String title;

    private String description;

    @NotNull
    private long user;

    public ProjectMinimum() {}

    public ProjectMinimum(String title, String description, long user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
