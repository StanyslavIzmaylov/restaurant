package com.github.stanyslavizmaylov.restaurant.util;

import com.github.stanyslavizmaylov.restaurant.model.Vote;
import com.github.stanyslavizmaylov.restaurant.to.VoteTo;

import java.time.LocalDate;

public class VoteUtil {

    public static Vote getTo(VoteTo voteTo) {
        return new Vote(voteTo.getId(), LocalDate.now());
    }
}
