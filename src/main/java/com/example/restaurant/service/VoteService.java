package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Vote;
import com.example.restaurant.repository.CrudRestaurantRepository;
import com.example.restaurant.repository.CrudUserRepository;
import com.example.restaurant.repository.CrudVoteRepository;
import com.example.restaurant.util.DateTimeUtil;
import com.example.restaurant.util.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static com.example.restaurant.util.ValidationUtil.*;

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

    public Vote getWithDateToDay(int id, int userId) {
        return voteRepository.getWithDateToDay(id, LocalDate.now(), userId);
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void update(Vote vote, int voteId, int userId) {
        Assert.notNull(vote, "vote must not be null");
        assureIdConsistent(vote, voteId);
        userCheck(vote, userId);
        vote.setUser(userRepository.getReferenceById(userId));

        if (getWithDateToDay(voteId, userId) != null) {
            timeRange(DateTimeUtil.getTimeNow());
            voteRepository.save(vote);
        }

        voteRepository.save(vote);
    }

    @Transactional
    public Vote save(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        userCheck(vote, userId);

        Restaurant restaurant = restaurantRepository.findById(vote.getRestaurant().getId()).orElse(null);
        Assert.notNull(restaurant, "restaurant must not be null");
        vote.setUser(userRepository.getReferenceById(userId));
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    public void userCheck(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.id(), userId) == null) {
            throw new NotFoundException("Entety not found");
        }
    }
}
