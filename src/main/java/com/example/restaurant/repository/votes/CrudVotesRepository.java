package com.example.restaurant.repository.votes;

import com.example.restaurant.model.Votes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrudVotesRepository extends JpaRepository<Votes, Integer> {

}
