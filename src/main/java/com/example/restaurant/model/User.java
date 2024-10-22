package com.example.restaurant.model;

import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    public User() {
    }

    public User(User u) {
        this(u.id, u.name, u.password, u.email, u.role);
    }

    public User(Integer id, String name, String password, String email, Role... role) {
        this(id, name, password, email, List.of(role));
    }

    public User(Integer id, String name, String password, String email, Collection<Role> role) {
        super(id, name);
        this.password = password;
        this.email = email;
        setRole(role);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Collection<Role> role) {
        this.role = CollectionUtils.isEmpty(role) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(role);
    }
}
