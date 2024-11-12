package com.example.restaurant.service;

import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import com.example.restaurant.repository.user.DataJpaUserRepository;
import com.example.restaurant.repository.vote.DataJpaVoteRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private final DataJpaVoteRepository voteRepository;

    @Autowired
    private final DataJpaRestaurantRepository restaurantRepository;

    @Autowired
    private final DataJpaUserRepository userRepository;

    public VoteService(DataJpaVoteRepository voteRepository, DataJpaRestaurantRepository restaurantRepository, DataJpaUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote get(int id) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.get(id), id);
    }

    public Vote getVoteWithUserId(int userId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.get(userId), userId);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void update(int restaurId, int userId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.get(restaurId), restaurId);
        ValidationUtil.checkNotFoundWithId(userRepository.get(userId), userId);
        voteRepository.save(restaurId, userId);
    }

    public Vote save(int restaurId, int userId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.get(restaurId), restaurId);
        ValidationUtil.checkNotFoundWithId(userRepository.get(userId), userId);
        return voteRepository.save(restaurId, userId);
    }
}
