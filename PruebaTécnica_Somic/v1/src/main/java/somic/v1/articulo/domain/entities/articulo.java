package somic.v1.articulo.domain.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class articulo {

    @Id
    private String codigo;
    
    private String nombre;
    
    private String laboratorio;
    
    private Integer saldo;
    
    private BigDecimal costo;
    
    private BigDecimal precioVenta;
}