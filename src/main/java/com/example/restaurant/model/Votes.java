package com.example.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Votes extends AbstractBaseEntity {

    @Column(name = "vote_date_time", nullable = false)
    private LocalDateTime voteDateTime;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Votes(LocalDateTime voteDateTime, Restaurant restaurant, User user) {
        this.voteDateTime = voteDateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Votes(Integer id, LocalDateTime voteDateTime, Restaurant restaurant, User user) {
        super(id);
        this.voteDateTime = voteDateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Votes() {
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

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    public void setVoteDateTime(LocalDateTime voteDateTime) {
        this.voteDateTime = voteDateTime;
    }
}
