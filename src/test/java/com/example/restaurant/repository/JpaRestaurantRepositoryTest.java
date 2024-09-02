package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JpaRestaurantRepositoryTest {

    @Autowired
    JpaRestaurantRepository jpaRestaurantRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {

        jpaRestaurantRepository.get(10);

    }
}
