package com.example.restaurant.to;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class VotesTo implements Serializable {

    private Integer id;

    private LocalDate date;

    private LocalTime localTime;

    private Integer restaurauntId;

    public VotesTo(Integer id, LocalDate date, LocalTime localTime, Integer restaurauntId) {
        this.id = id;
        this.date = date;
        this.localTime = localTime;
        this.restaurauntId = restaurauntId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Integer getRestaurauntId() {
        return restaurauntId;
    }

    public void setRestaurauntId(Integer restaurauntId) {
        this.restaurauntId = restaurauntId;
    }
}
