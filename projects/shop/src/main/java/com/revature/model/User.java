package com.revature.model;


import java.util.Objects;

public class User {
    public enum Role {
        CUSTOMER, EMPLOYEE, MANAGER
    }

    private int id;
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}