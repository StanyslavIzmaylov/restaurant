package com.example.restaurant.repository.vote;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.restaurant.CrudRestaurantRepository;
import com.example.restaurant.repository.user.CrudUserRepository;
import com.example.restaurant.util.ValidationUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class DataJpaVoteRepository {
    private final CrudUserRepository crudUserRepository;
    private final CrudVoteRepository crudVoteRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudUserRepository = crudUserRepository;
        this.crudVoteRepository = crudVoteRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    public Vote getVoteWithUserId(int userId) {
        return crudVoteRepository.getVoteWithUserId(userId);
    }

    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    @Transactional
    public Vote save(int restaurId, int userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ValidationUtil.timeRange(localDateTime);
        Restaurant restaurant = crudRestaurantRepository.findById(restaurId).orElse(null);
        User user = crudUserRepository.getReferenceById(userId);

        if (getVoteWithUserId(userId) != null) {
            delete(getVoteWithUserId(userId).getId());
        }
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        vote.setVoteDateTime(localDateTime);
        return crudVoteRepository.save(vote);
    }
}
