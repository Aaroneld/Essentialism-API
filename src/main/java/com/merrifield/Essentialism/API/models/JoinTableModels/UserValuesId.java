package com.merrifield.Essentialism.API.models.JoinTableModels;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserValuesId implements Serializable {
    private long user;
    private long value;

    public UserValuesId() {}

    public UserValuesId(long user, long value) {
        this.user = user;
        this.value = value;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
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
        if (!(o instanceof UserValuesId)) return false;
        UserValuesId that = (UserValuesId) o;
        return getUser() == that.getUser() &&
                getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return 34;
    }
}
