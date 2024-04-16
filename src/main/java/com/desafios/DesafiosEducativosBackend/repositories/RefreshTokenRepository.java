package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.RefreshToken;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
}
