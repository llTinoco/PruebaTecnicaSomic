package somic.v1.articulo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import somic.v1.articulo.domain.entities.articulo;

public interface articuloRepository extends JpaRepository<articulo, String> {
}