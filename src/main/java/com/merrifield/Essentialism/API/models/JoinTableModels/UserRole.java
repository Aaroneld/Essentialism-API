package com.merrifield.Essentialism.API.models.JoinTableModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.merrifield.Essentialism.API.models.Role;
import com.merrifield.Essentialism.API.models.UserModels.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "roles", allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties(value = "userRoles", allowSetters = true)
    private Role role;

    public UserRole() {}

    public UserRole(@NotNull User user, @NotNull Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(getUser(), userRole.getUser()) &&
                Objects.equals(getRole(), userRole.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getRole());
    }
}
