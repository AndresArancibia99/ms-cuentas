package cl.everis.cuentas.dto;


import cl.everis.cuentas.model.TipoCuenta;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaRequestDTO implements Serializable {



    private String rut;
    private long numeroCuenta;
    private List<TipoCuenta> tipoCuenta;
    private String fechaCreacion;
}
