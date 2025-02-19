package somic.v1.nit.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import somic.v1.nit.domain.entities.nit;

public interface nitRepository extends JpaRepository<nit, String> {
}