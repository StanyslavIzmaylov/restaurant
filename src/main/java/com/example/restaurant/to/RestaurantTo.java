package com.example.restaurant.to;

import java.io.Serializable;

public class RestaurantTo implements Serializable {
    private Integer id;
    private String name;

    public RestaurantTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
