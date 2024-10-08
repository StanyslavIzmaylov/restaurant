package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class DataJpaRestaurantRepositoryTest {

    @Autowired
    DataJpaRestaurantRepository jpaRestaurantRepository;
    @Test
    public void save() {
        Restaurant restaurant = new Restaurant(null,"Новый Ресторан");
       jpaRestaurantRepository.save(restaurant);

    }
    @Test
    public void getAll() {
    List<Restaurant> menus = jpaRestaurantRepository.getAllWithMenu();
    menus.size();
    }
}
