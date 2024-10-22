package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private final DataJpaRestaurantRepository restaurantRepository;

    public RestaurantService(DataJpaRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "menu must not be null");
        restaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "menu must not be null");
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }
}
