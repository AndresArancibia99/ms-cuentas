package cl.everis.cuentas.model;


import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tipo_cuenta")
public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoCuenta;

    private String tipodeCuenta;

    private String descripcionCuenta;


    @ManyToOne
    @JoinColumn(name = "rut")
    private Cuenta cuenta;
}
