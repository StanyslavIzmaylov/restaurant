package com.example.restaurant.repository;

import com.example.restaurant.data.RestaurantDataTest;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.util.exeption.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RestaurantServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    void delete() {
        restaurantService.delete(RestaurantDataTest.RESTAUR_ID);
        assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantDataTest.RESTAUR_ID));
    }

    @Test
    public void get() {
        Restaurant restaurant = restaurantService.get(RestaurantDataTest.RESTAUR_ID);
        RestaurantDataTest.RESTAURANT_MATCHER.assertMatch(RestaurantDataTest.restaurant,restaurant);
    }
    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantDataTest.RESTAURANT_NOT_FOUND));
    }
    @Test
    public void update() {
        Restaurant restaurant = RestaurantDataTest.getUpdate();
        restaurantService.update(restaurant);
        RestaurantDataTest.RESTAURANT_MATCHER.assertMatch(restaurantService.get(RestaurantDataTest.RESTAUR_ID),restaurant);
    }
    @Test
    public void save() {
        Restaurant created = restaurantService.save(RestaurantDataTest.getNew());
        int newId = created.getId();
        Restaurant newRestaurant = RestaurantDataTest.getNew();
        newRestaurant.setId(newId);
        RestaurantDataTest.RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RestaurantDataTest.RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }
}
