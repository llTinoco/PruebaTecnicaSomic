package somic.v1.nit.domain.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class nit {

    @Id
    private String documento;
    
    private String nombre;
    
    private BigDecimal cupo;
    
    private Integer plazo;
}