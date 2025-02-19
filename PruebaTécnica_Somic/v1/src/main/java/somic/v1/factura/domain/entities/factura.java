package somic.v1.factura.domain.entities; 

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import somic.v1.articulo.domain.entities.articulo;
import somic.v1.nit.domain.entities.nit;

@Entity
@Table(name = "factura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate fecha;
    
    private Integer unidades;
    
    @Column(name = "total_venta")
    private BigDecimal totalVenta;
    
    @ManyToOne
    @JoinColumn(name = "nit_documento")
    private nit nit;
    
    @ManyToOne
    @JoinColumn(name = "art_codigo")
    private articulo art;
}