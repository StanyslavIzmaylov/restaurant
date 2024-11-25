package com.github.stanyslavizmaylov.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"vote_date", "user_id"}, name = "vote_unique_date_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;
    @Column(name = "vote_time", nullable = false)
    private LocalTime localTime;
    @ManyToOne()
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Vote(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Vote(Integer id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
    }

    public Vote() {
    }

    public Vote(Integer id, LocalDate voteDate) {
        super(id);
        this.voteDate = voteDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
