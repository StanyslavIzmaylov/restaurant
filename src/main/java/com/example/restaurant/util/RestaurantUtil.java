package com.example.restaurant.util;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.to.RestaurantTo;

public class RestaurantUtil {

    public static RestaurantTo asTo (Restaurant restaurant){
        return new RestaurantTo(restaurant.getId(),restaurant.getName());
    }
}
