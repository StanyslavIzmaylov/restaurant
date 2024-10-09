package com.example.restaurant.repository.votes;

import com.example.restaurant.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudVotesRepository extends JpaRepository<Votes, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Votes v WHERE v.id=:id")
    int delete(@Param("id") int id);
}
