package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findUserByUsernameAndPassword( String username, String password);

    Optional<User> findByUsername(String username);
}
