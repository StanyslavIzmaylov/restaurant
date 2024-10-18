package com.example.restaurant.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VotesTo implements Serializable {

    private Integer id;

    private LocalDateTime localDateTime;

    private Integer restaurauntId;


    public VotesTo(Integer id, LocalDateTime localDateTime, Integer restaurauntId) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.restaurauntId = restaurauntId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Integer getRestaurauntId() {
        return restaurauntId;
    }

    public void setRestaurauntId(Integer restaurauntId) {
        this.restaurauntId = restaurauntId;
    }
}
