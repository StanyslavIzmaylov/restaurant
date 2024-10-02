package com.example.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    public static final int START = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    private String name;
      @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
      List<Menu> menus;

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
//    Set<User> votes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant() {
    }

}
