package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ers_users")
public class ERSUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ers_users_id", nullable = false)
    private Integer id;

    @Column(name = "ers_username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "ers_password", nullable = false, length = 50)
    private String password;

    @Column(name = "user_first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "user_last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "user_email", nullable = false, unique = true, length = 150)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_role_id", nullable = false)
    private ERSUserRole ersUserRole;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        ERSUser ersUser = (ERSUser) o;
        return Objects.equals(id, ersUser.id) && Objects.equals(username, ersUser.username) && Objects.equals(password, ersUser.password) && Objects.equals(firstName, ersUser.firstName) && Objects.equals(lastName, ersUser.lastName) && Objects.equals(email, ersUser.email) && Objects.equals(ersUserRole, ersUser.ersUserRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, ersUserRole);
    }

    @Override
    public String toString() {
        return "ERSUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ersUserRole=" + ersUserRole +
                '}';
    }
}