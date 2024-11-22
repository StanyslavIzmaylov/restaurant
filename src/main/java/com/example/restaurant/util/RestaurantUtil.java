package com.example.restaurant.util;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.to.RestaurantTo;

public class RestaurantUtil {

    public static Restaurant getTo(RestaurantTo restaurantTo) {
        return new Restaurant(restaurantTo.getId(), restaurantTo.getName());
    }
}
