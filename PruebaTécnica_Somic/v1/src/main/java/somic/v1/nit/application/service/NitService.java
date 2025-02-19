package somic.v1.nit.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import somic.v1.nit.domain.entities.nit;
import somic.v1.nit.infrastructure.repository.nitRepository;

@Service
public class NitService {
    
    private final nitRepository nitRepository;
    
    public NitService(nitRepository nitRepository) {
        this.nitRepository = nitRepository;
    }
    
    public nit createNit(nit nit) {
        return nitRepository.save(nit);
    }
    
    public List<nit> getAllNits() {
        return nitRepository.findAll();
    }
    
    public Optional<nit> getNitByDocumento(String documento) {
        return nitRepository.findById(documento);
    }
    
    public nit updateNit(nit nit) {
        return nitRepository.save(nit);
    }
    
    public void deleteNit(String documento) {
        nitRepository.deleteById(documento);
    }
}