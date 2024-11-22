package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.CrudRestaurantRepository;
import com.example.restaurant.repository.CrudUserRepository;
import com.example.restaurant.repository.CrudVoteRepository;
import com.example.restaurant.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.example.restaurant.util.DateTimeUtil.timeRange;
import static com.example.restaurant.util.ValidationUtil.assureIdConsistent;
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

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(voteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null), id);
    }

    public Vote getWithDateToDay(int userId) {
        return voteRepository.getVoteByUserAndDateToDay(userId, LocalDate.now());
    }

    private Vote getVoteByUserAndDateToDay(int userId) {
        return voteRepository.getVoteByUserAndDateToDay(userId, LocalDate.now());
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void update(Vote vote, int voteId, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        assureIdConsistent(vote, voteId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        Assert.notNull(restaurant, "restaurant must not be null");
        vote.setUser(userRepository.getReferenceById(userId));
        vote.setRestaurant(restaurant);

        if (getVoteByUserAndDateToDay(userId) != null) {
            timeRange(DateTimeUtil.getTimeNow());
            voteRepository.save(vote);
        }
    }

    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        if (!vote.isNew() && get(vote.id(), userId) != null) {
            return null;
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        Assert.notNull(restaurant, "restaurant must not be null");
        vote.setUser(userRepository.getReferenceById(userId));
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    public List<Vote> getAllWithUser(int userId) {
        return voteRepository.getVoteByUser(userId);
    }

    public List<Vote> geWithDate(LocalDate localDate) {
        return voteRepository.geWithDate(localDate);
    }
}
