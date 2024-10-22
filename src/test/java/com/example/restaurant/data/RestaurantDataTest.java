package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Restaurant;

public class RestaurantDataTest {

    public static final int RESTAUR_ID = 100001;

    public static final int  RESTAURANT_NOT_FOUND = 20;

    public static Restaurant restaurant = new Restaurant(RESTAUR_ID,"Кофейня");

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus");

    public static Restaurant getUpdate(){
        return  new Restaurant(RESTAUR_ID,"Обновленное название");
    }

    public static Restaurant getNew(){
        return  new Restaurant(null,"Новый ресторан");
    }
}
