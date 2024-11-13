package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.CrudRestaurantRepository;
import com.example.restaurant.repository.CrudUserRepository;
import com.example.restaurant.repository.CrudVoteRepository;
import com.example.restaurant.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final CrudVoteRepository voteRepository;

    private final CrudRestaurantRepository restaurantRepository;

    private final CrudUserRepository userRepository;

    public VoteService(CrudVoteRepository voteRepository, CrudRestaurantRepository restaurantRepository, CrudUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    public Vote get(int id) {
        return checkNotFoundWithId(voteRepository.findById(id).orElse(null),id);
    }

    public Vote getVoteWithUserId(int userId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.getByUserId(userId), userId);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void update(int restaurId, int userId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.findById(restaurId), restaurId);
        ValidationUtil.checkNotFoundWithId(userRepository.findById(userId).orElse(null), userId);
         save(restaurId, userId);
    }
    @Transactional
    public Vote save(int restaurId, int userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ValidationUtil.timeRange(localDateTime);
        Restaurant restaurant = restaurantRepository.findById(restaurId).orElse(null);
        User user = userRepository.getReferenceById(userId);

        if (getVoteWithUserId(userId) != null) {
            delete(getVoteWithUserId(userId).getId());
        }
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        vote.setVoteDateTime(localDateTime);
        return voteRepository.save(vote);
    }
}
