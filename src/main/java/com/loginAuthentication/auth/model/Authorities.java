package com.loginAuthentication.auth.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorities;
    private boolean role;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", authorities='" + authorities + '\'' +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}
