package com.example.restaurant.repository.user;

import com.example.restaurant.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaUserRepository {

    private final CrudUserRepository crudUserRepository;

    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email){
       return crudUserRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll();
    }
}
