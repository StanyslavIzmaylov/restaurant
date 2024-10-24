package com.example.restaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {
    @NotBlank
    @Size(min = 5, max = 128)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;
    @Email
    @NotBlank
    @Size(max = 128)
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
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
