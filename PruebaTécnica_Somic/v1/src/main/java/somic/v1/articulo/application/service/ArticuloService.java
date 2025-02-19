package somic.v1.articulo.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import somic.v1.articulo.domain.entities.articulo;
import somic.v1.articulo.infrastructure.repository.articuloRepository;

@Service
public class ArticuloService {
    
    private final articuloRepository articuloRepository;
    
    public ArticuloService(articuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }
    
    public articulo createArticulo(articulo articulo) {
        return articuloRepository.save(articulo);
    }
    
    public List<articulo> getAllArticulos() {
        return articuloRepository.findAll();
    }
    
    public Optional<articulo> getArticuloByCodigo(String codigo) {
        return articuloRepository.findById(codigo);
    }
    
    public articulo updateArticulo(articulo articulo) {
        return articuloRepository.save(articulo);
    }
    
    public void deleteArticulo(String codigo) {
        articuloRepository.deleteById(codigo);
    }
}