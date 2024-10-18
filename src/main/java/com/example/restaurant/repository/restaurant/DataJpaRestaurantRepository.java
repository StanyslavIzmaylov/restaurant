package com.example.restaurant.repository.restaurant;

import com.example.restaurant.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }
}
