package com.example.restaurant.util;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.to.RestaurantTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RestaurantUtil {

    public static RestaurantTo asTo (Restaurant restaurant){
        return new RestaurantTo(restaurant.getId(),restaurant.getName());
    }

    public static List<Restaurant> filterByDate(List<Restaurant> restaurants, LocalDate localDate){
        List<Restaurant> filter = new ArrayList<>();
        return  null;
    }
}
