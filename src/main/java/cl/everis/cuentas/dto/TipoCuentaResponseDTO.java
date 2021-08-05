package cl.everis.cuentas.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoCuentaResponseDTO implements Serializable {

    private int idTipoCuenta;

    private String tipoCuenta;

    private String descripcionCuenta;

}
