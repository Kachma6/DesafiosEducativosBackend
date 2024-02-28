package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RepDesaRepository extends JpaRepository<RepDesa,Integer> {
  List<RepDesa> findAllByDesaJoin_Id(Integer idDesaJoin);
}
