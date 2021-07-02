package com.merrifield.Essentialism.API.models.JoinTableModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_values")
@IdClass(UserValuesId.class)
public class UserValue implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "userValues", allowSetters = true)
    private User user;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "value_id")
    private Value value;

    @Column
    private boolean favorite;

    @Column
    private String text;

    public UserValue() {}

    public UserValue(User user, Value value, boolean favorite, String text) {
        this.user = user;
        this.value = value;
        this.favorite = favorite ? favorite : false;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getText() {
        return text;
    }

    public void setTest(String test) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserValue)) return false;
        UserValue userValue = (UserValue) o;
        return isFavorite() == userValue.isFavorite() &&
                Objects.equals(getUser(), userValue.getUser()) &&
                Objects.equals(getValue(), userValue.getValue()) &&
                Objects.equals(getText(), userValue.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getValue(), isFavorite(), getText());
    }
}
