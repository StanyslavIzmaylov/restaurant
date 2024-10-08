package com.example.restaurant.model;

import com.example.restaurant.util.ValidDateRange;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
public class Votes {
    @Id
    @Column(name = "user_id")
    private int id;
    @ValidDateRange
    @Column(name = "created", columnDefinition = "timestamp default now()")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    public Votes(Restaurant restaurant) {
        this.localDateTime = LocalDateTime.now();
    }

    public Votes() {
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
