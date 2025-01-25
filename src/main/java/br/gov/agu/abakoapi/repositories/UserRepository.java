package br.gov.agu.abakoapi.repositories;

import br.gov.agu.abakoapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
