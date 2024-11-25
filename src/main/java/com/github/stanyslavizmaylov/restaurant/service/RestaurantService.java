package com.github.stanyslavizmaylov.restaurant.service;

import com.github.stanyslavizmaylov.restaurant.model.Restaurant;
import com.github.stanyslavizmaylov.restaurant.repository.CrudRestaurantRepository;
import com.github.stanyslavizmaylov.restaurant.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public RestaurantService(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(crudRestaurantRepository.delete(id), id);
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(crudRestaurantRepository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getWithAllMenu(int id) {
        return crudRestaurantRepository.getWithAllMenu(id);
    }

    public Restaurant getWithDate(int id, LocalDate localDate) {
        return crudRestaurantRepository.getWithDate(id, localDate);
    }

    public void update(Restaurant restaurant, int restaurId) {
        Assert.notNull(restaurant, "menu must not be null");
        ValidationUtil.assureIdConsistent(restaurant, restaurId);
        crudRestaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "menu must not be null");
        if (!restaurant.isNew() && get(restaurant.id()) != null) {
            return null;
        }
        return crudRestaurantRepository.save(restaurant);
    }

    public List<Restaurant> getWithMenuAndWithDate(LocalDate localDate) {
        return crudRestaurantRepository.getWithMenuAndWithDate(localDate);
    }
}
