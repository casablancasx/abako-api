package br.gov.agu.abakoapi.repositories;

import br.gov.agu.abakoapi.entities.BeneficioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficioRepository extends JpaRepository<BeneficioEntity, Long> {

    Optional<BeneficioEntity> findByNome(String nome);
}
