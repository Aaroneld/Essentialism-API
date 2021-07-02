package com.merrifield.Essentialism.API.models.JoinTableModels;

import com.merrifield.Essentialism.API.models.ProjectModels.Project;
import com.merrifield.Essentialism.API.models.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "project_values")
@IdClass(ProjectValueId.class)
public class ProjectValue implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "value_id")
    private Value value;

    public ProjectValue() {}

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectValue)) return false;
        ProjectValue that = (ProjectValue) o;
        return Objects.equals(getProject(), that.getProject()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProject(), getValue());
    }
}
