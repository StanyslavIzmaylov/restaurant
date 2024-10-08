package com.example.restaurant.repository.votes;

import com.example.restaurant.model.User;
import com.example.restaurant.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotesRepository {
    Votes save(Votes votes, int restuarId);

}
