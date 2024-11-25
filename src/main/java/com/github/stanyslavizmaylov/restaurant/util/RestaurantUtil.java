package com.github.stanyslavizmaylov.restaurant.util;

import com.github.stanyslavizmaylov.restaurant.model.Restaurant;
import com.github.stanyslavizmaylov.restaurant.to.RestaurantTo;

public class RestaurantUtil {

    public static Restaurant getTo(RestaurantTo restaurantTo) {
        return new Restaurant(restaurantTo.getId(), restaurantTo.getName());
    }
}
