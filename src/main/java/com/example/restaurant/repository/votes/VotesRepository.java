package com.example.restaurant.repository.votes;

import com.example.restaurant.model.Votes;

public interface VotesRepository {
    Votes get(int userId);

    boolean delete(int userId);

    Votes save(Votes votes, int userId);

}
