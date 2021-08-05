package cl.everis.cuentas.dto;

import cl.everis.cuentas.model.Cuenta;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoCuentaRequestDTO implements Serializable {

    private int idTipoCuenta;
    private String tipoCuenta;
    private String descripcionCuenta;
    private Cuenta cuenta;
}
