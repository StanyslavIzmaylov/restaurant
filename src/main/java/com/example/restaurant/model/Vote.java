package com.example.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    private LocalDate voteDate;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Vote(Restaurant restaurant, User user) {
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(Integer id, Restaurant restaurant, User user) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
    }


    public Vote(Integer id, LocalDate voteDate, Restaurant restaurant, User user) {
        super(id);
        this.voteDate = voteDate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote() {
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

}
