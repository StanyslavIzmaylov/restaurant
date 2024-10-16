package com.example.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "votes_date"}, name = "votes_unique_user_date_idx")})
public class Votes extends AbstractBaseEntity {

    @Column(name = "votes_date", nullable = false)
    private LocalDate localDate;

    @Column(name = "vote_time", nullable = false)
    private LocalTime localTime;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @JsonIgnore
    private User user;

    public Votes() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
