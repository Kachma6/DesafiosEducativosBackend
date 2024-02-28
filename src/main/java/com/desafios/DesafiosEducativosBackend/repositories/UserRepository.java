package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
}
