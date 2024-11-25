package com.github.stanyslavizmaylov.restaurant.data;

import com.github.stanyslavizmaylov.restaurant.MatcherFactory;
import com.github.stanyslavizmaylov.restaurant.model.Restaurant;
import com.github.stanyslavizmaylov.restaurant.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoteDataTest {

    public static final int VOTE_ID = 100029;

    public static final int VOTE_NOT_FOUND = 100;

    public static Restaurant restaurant = new Restaurant(RestaurantDataTest.RESTAUR_ID - 1, "Кофейня");
    public static Restaurant restaurant1 = new Restaurant(RestaurantDataTest.RESTAUR_ID + 2, "Столовая");
    public static Vote vote = getVote();

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user","localTime","restaurant");


    public static Vote getVote() {
        Vote vote = new Vote(VOTE_ID, restaurant);
        vote.setVoteDate(LocalDate.now());
        vote.setLocalTime(LocalTime.of(10,0));
        vote.setUser(UserDataTest.user);
        return vote;
    }

    public static Vote getUpdate() {
        Vote vote = new Vote(VOTE_ID, restaurant1);
        vote.setVoteDate(LocalDate.now());
        vote.setLocalTime(LocalTime.now());
        vote.setUser(UserDataTest.user);
        return vote;
    }



    public static Vote getNew() {
        Vote vote = new Vote(null, restaurant);
        vote.setVoteDate(LocalDate.now());
        vote.setLocalTime(LocalTime.now());
        vote.setUser(UserDataTest.user2);
        return vote;
    }
}
