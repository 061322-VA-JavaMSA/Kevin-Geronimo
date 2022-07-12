package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ers_user_roles")
public class ERSUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ers_user_role_id", nullable = false)
    private Integer id;

    @Column(name = "user_role", nullable = false, length = 10)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSUserRole that = (ERSUserRole) o;
        return Objects.equals(id, that.id) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "ERSUserRoles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}