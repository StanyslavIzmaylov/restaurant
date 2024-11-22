package com.example.restaurant.util;

import com.example.restaurant.model.Vote;
import com.example.restaurant.to.VoteTo;

import java.time.LocalDate;

public class VoteUtil {

    public static Vote getTo(VoteTo voteTo) {
        return new Vote(voteTo.getId(), LocalDate.now());
    }
}
