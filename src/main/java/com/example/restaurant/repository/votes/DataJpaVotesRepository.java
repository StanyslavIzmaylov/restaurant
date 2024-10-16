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
public class DataJpaVotesRepository implements VotesRepository {
    private final CrudUserRepository crudUserRepository;
    private final CrudVotesRepository crudVotesRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVotesRepository(CrudUserRepository crudUserRepository, CrudVotesRepository crudVotesRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudUserRepository = crudUserRepository;
        this.crudVotesRepository = crudVotesRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Votes get(int userId) {
        return crudVotesRepository.findById(userId).orElse(null);
    }

    public boolean delete(int userId) {
        return crudVotesRepository.delete(userId) !=0;
    }


    @Override
    @Transactional
    public Votes save(Votes votes, int userId) {
        ValidationUtil.timeRange(votes.getLocalTime());

        Restaurant restaurant = crudRestaurantRepository.findById(votes.getRestaurant().getId()).orElse(null);
        User user = crudUserRepository.getReferenceById(userId);

        if (restaurant == null || user == null) {
            throw new NullPointerException();
        }

        votes.setRestaurant(restaurant);
        return crudVotesRepository.save(votes);
    }
}
