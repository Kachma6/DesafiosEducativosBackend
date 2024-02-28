package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
