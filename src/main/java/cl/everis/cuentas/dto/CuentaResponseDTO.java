package cl.everis.cuentas.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaResponseDTO implements Serializable {

    private String rut;

    private long NumeroCuenta;

    private List<TipoCuentaResponseDTO> tipoCuenta;
}
