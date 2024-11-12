package com.example.restaurant.data;

import com.example.restaurant.MatcherFactory;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.User;
import com.example.restaurant.model.Vote;

public class VoteDataTest {

    public static final int VOTE_ID = 100029;

    public static final int VOTE_NOT_FOUND = 100;

    public static Restaurant restaurant = new Restaurant(RestaurantDataTest.RESTAUR_ID - 1, "Кофейня");
    public static User user = new User();
    public static Vote vote = new Vote(VOTE_ID, restaurant, user);

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant","voteDateTime");

    public static Vote getUpdate() {
        return new Vote(VOTE_ID, restaurant, user);
    }

    public static Vote getNew() {

        return new Vote(null, restaurant, user);
    }
}
