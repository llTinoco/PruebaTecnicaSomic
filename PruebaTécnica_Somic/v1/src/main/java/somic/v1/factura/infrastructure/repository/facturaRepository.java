package somic.v1.factura.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import somic.v1.factura.domain.entities.factura;

@Repository
public interface facturaRepository extends JpaRepository<factura, Long> {
}