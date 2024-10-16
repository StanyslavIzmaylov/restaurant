package com.example.restaurant.util;

import com.example.restaurant.model.Votes;
import com.example.restaurant.to.VotesTo;

public class VotesUtil {

    public static VotesTo asTo(Votes votes){
        return new VotesTo(votes.getId(),votes.getLocalDate(),votes.getLocalTime(),votes.getRestaurant().getId());
    }
}
