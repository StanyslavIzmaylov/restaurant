package com.example.restaurant.service;

import com.example.restaurant.model.Votes;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import com.example.restaurant.repository.user.DataJpaUserRepository;
import com.example.restaurant.repository.votes.DataJpaVotesRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotesService {

    @Autowired
    private final DataJpaVotesRepository votesRepository;

    @Autowired
    private final DataJpaRestaurantRepository restaurantRepository;

    @Autowired
    private final DataJpaUserRepository userRepository;

    public VotesService(DataJpaVotesRepository votesRepository, DataJpaRestaurantRepository restaurantRepository, DataJpaUserRepository userRepository) {
        this.votesRepository = votesRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Votes get(int id) {
        return ValidationUtil.checkNotFoundWithId(votesRepository.get(id), id);
    }

    public Votes getVotesWithUserId(int userId) {
        return ValidationUtil.checkNotFoundWithId(votesRepository.get(userId), userId);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(votesRepository.delete(id), id);
    }

    public void update(int restaurId, int userId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.get(restaurId), restaurId);
        ValidationUtil.checkNotFoundWithId(userRepository.get(userId), userId);
        votesRepository.save(restaurId, userId);
    }

    public Votes save(int restaurId, int userId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.get(restaurId), restaurId);
        ValidationUtil.checkNotFoundWithId(userRepository.get(userId), userId);
        return votesRepository.save(restaurId, userId);
    }
}
