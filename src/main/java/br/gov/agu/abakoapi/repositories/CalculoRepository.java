package br.gov.agu.abakoapi.repositories;

import br.gov.agu.abakoapi.entities.CalculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRepository extends JpaRepository<CalculoEntity,Long> {
}
