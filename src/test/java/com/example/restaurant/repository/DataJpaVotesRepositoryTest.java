package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import com.example.restaurant.repository.user.DataJpaUserRepository;
import com.example.restaurant.repository.votes.DataJpaVotesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class DataJpaVotesRepositoryTest {

    @Autowired
    DataJpaVotesRepository dataJpaVotesRepository;

    @Autowired
    DataJpaRestaurantRepository dataJpaRestaurantRepository;

    @Autowired
    DataJpaUserRepository dataJpaUserRepository;

    @Test
    @Transactional
    public void save() {
        Restaurant restaurant = dataJpaRestaurantRepository.get(100001);
        User user = dataJpaUserRepository.get(100019);
        Votes votes = new Votes();
        dataJpaVotesRepository.save(votes);
    }
}