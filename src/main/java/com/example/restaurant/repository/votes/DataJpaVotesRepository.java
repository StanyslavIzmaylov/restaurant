package com.example.restaurant.repository.votes;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.restaurant.CrudRestaurantRepository;
import com.example.restaurant.repository.user.CrudUserRepository;
import com.example.restaurant.util.ValidationUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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

    public Votes get(int id) {
        return crudVotesRepository.findById(id).orElse(null);
    }

    public Votes getVotesWithUserId(int userId) {
        return crudVotesRepository.getVotesWithUserId(userId);
    }

    public boolean delete(int id) {
        return crudVotesRepository.delete(id) != 0;
    }

    @Transactional
    public Votes save(int restaurId, int userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ValidationUtil.timeRange(localDateTime);
        Restaurant restaurant = crudRestaurantRepository.findById(restaurId).orElse(null);
        User user = crudUserRepository.getReferenceById(userId);

        if (getVotesWithUserId(userId) != null) {
            delete(getVotesWithUserId(userId).getId());
        }

        Votes votes = new Votes(localDateTime, restaurant, user);
        return crudVotesRepository.save(votes);
    }
}
