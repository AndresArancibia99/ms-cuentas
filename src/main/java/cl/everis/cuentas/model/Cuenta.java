package cl.everis.cuentas.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cuentas")
public class Cuenta {


    //@Id
    //private long idCuenta;

    @Id
    private String rut;

    @Column(name = "num_cuenta")
    private long numeroCuenta;

    @OneToMany(mappedBy = "cuenta")
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    private List<TipoCuenta> tipoCuenta;

    @Column(name="fechaCreacion")
    private String fechaCreacion;
}
