package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.CrudRestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.restaurant.util.ValidationUtil.assureIdConsistent;
import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public RestaurantService(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    public void delete(int id) {
        checkNotFoundWithId(crudRestaurantRepository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(crudRestaurantRepository.findById(id).orElse(null), id);
    }

    public void update(Restaurant restaurant, int restaurId) {
        Assert.notNull(restaurant, "menu must not be null");
        assureIdConsistent(restaurant,restaurId);
        crudRestaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "menu must not be null");
        return crudRestaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }
}
