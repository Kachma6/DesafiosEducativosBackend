package com.desafios.DesafiosEducativosBackend.services;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUserById(Integer id);
    User save(User user);
    void delete(Integer id);

    User getUserByUsernameAndPassword(String username, String password);
}
