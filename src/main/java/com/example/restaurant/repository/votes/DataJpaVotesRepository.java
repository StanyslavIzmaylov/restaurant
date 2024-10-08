package com.example.restaurant.repository.votes;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.restaurant.CrudRestaurantRepository;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import com.example.restaurant.repository.user.CrudUserRepository;
import com.example.restaurant.repository.user.DataJpaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaVotesRepository {
    private final CrudUserRepository crudUserRepository;
    private final CrudVotesRepository crudVotesRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVotesRepository(CrudUserRepository crudUserRepository, CrudVotesRepository crudVotesRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudUserRepository = crudUserRepository;
        this.crudVotesRepository = crudVotesRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    @Transactional
    public Votes save(Votes votes, int userId) {
        return crudVotesRepository.save(votes);
    }
}
