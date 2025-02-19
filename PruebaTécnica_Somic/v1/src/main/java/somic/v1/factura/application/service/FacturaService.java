package somic.v1.factura.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import somic.v1.factura.domain.entities.factura;
import somic.v1.factura.infrastructure.repository.facturaRepository;

@Service
public class FacturaService {
    
    private final facturaRepository facturaRepository;
    
    public FacturaService(facturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }
    
    public factura createFactura(factura factura) {
        return facturaRepository.save(factura);
    }
    
    public List<factura> getAllFacturas() {
        return facturaRepository.findAll();
    }
    
    public Optional<factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }
    
    public factura updateFactura(factura factura) {
        return facturaRepository.save(factura);
    }
    
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    public factura save(factura fac) {
        return facturaRepository.save(fac);
    }
}