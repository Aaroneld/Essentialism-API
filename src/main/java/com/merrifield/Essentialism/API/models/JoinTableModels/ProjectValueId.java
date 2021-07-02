package com.merrifield.Essentialism.API.models.JoinTableModels;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectValueId implements Serializable {
    private long project;
    private long value;

    public ProjectValueId() {}

    public ProjectValueId(long project, long value) {
        this.value = value;
        this.project = project;
    }


    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectValueId)) return false;
        ProjectValueId that = (ProjectValueId) o;
        return getProject() == that.getProject() &&
                value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProject(), value);
    }
}
