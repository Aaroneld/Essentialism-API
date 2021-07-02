package com.merrifield.Essentialism.API.models.ProjectModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.merrifield.Essentialism.API.models.JoinTableModels.ProjectValue;
import com.merrifield.Essentialism.API.models.UserModels.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    @NotNull
    String title;

    @Column
    String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "project", allowSetters = true)
    private List<ProjectValue> projectValues = new ArrayList<>();



    public Project() {}

    public Project(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Project(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Project(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<ProjectValue> getProjectValues() {
        return projectValues;
    }

    public void setProjectValues(List<ProjectValue> projectValues) {
        this.projectValues = projectValues;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
