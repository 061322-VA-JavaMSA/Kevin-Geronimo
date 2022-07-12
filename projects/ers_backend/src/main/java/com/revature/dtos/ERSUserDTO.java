package com.revature.dtos;

import com.revature.models.ERSUser;
import com.revature.models.ERSUserRole;

import java.util.Objects;

public class ERSUserDTO {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private ERSUserRole ersUserRole;

    public ERSUserDTO() {
        super();
    }

    public ERSUserDTO(ERSUser user) {
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        ersUserRole = user.getErsUserRole();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ERSUserRole getErsUserRole() {
        return ersUserRole;
    }

    public void setErsUserRole(ERSUserRole ersUserRole) {
        this.ersUserRole = ersUserRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSUserDTO that = (ERSUserDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(ersUserRole, that.ersUserRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, email, ersUserRole);
    }

    @Override
    public String toString() {
        return "ERSUserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ersUserRole=" + ersUserRole +
                '}';
    }
}
