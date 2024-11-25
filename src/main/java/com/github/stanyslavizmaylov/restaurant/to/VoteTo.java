package com.github.stanyslavizmaylov.restaurant.to;

import java.io.Serializable;

public class VoteTo implements Serializable {

    private Integer id;

    private Integer restaurantId;

    public VoteTo(Integer id, Integer restaurantId) {
        this.id = id;
        this.restaurantId = restaurantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
