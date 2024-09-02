package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRestaurantRepository{

    CrudRestaurantRepository crudRestaurantRepository;

    public JpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Restaurant save(Restaurant restaurant){
       return crudRestaurantRepository.save(restaurant);
    }

    public Restaurant get(Integer id){
         return crudRestaurantRepository.findById(id).orElse(null);
    }
}
