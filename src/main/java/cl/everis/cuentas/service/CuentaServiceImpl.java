package cl.everis.cuentas.service;

import cl.everis.cuentas.dto.CuentaRequestDTO;
import cl.everis.cuentas.dto.CuentaResponseDTO;
import cl.everis.cuentas.dto.TipoCuentaResponseDTO;
import cl.everis.cuentas.exception.ErrorException;
import cl.everis.cuentas.model.Cuenta;
import cl.everis.cuentas.model.TipoCuenta;
import cl.everis.cuentas.repository.CuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {


    private CuentasRepository cuentasRepository;

    public CuentaServiceImpl(CuentasRepository cuentasRepository) {
        this.cuentasRepository = cuentasRepository;
    }

    @Override
    public List<CuentaResponseDTO> findAll() {

        List<Cuenta> listaCuentas = cuentasRepository.findAll();

        List<CuentaResponseDTO> listaCuentasResponseDTO = new ArrayList<>();

        listaCuentas.forEach(cuenta -> {
            List<TipoCuentaResponseDTO> ListaTipoCuentaResponseDTOS = cuenta.getTipoCuenta()
                    .stream()
                    .map(tipoCuenta -> TipoCuentaResponseDTO.builder()
                            .idTipoCuenta(tipoCuenta.getIdTipoCuenta())
                            .tipoCuenta(tipoCuenta.getTipodeCuenta())
                            .descripcionCuenta(tipoCuenta.getDescripcionCuenta())
                            .build())
                            .collect(Collectors.toList());

                    listaCuentasResponseDTO.add(CuentaResponseDTO.builder()
                            .rut(cuenta.getRut())
                            .NumeroCuenta(cuenta.getNumeroCuenta())
                            .tipoCuenta(ListaTipoCuentaResponseDTOS)
                            .build());
                }
        );
        return listaCuentasResponseDTO;


    }

    @Override
    public void insertarCuenta(CuentaRequestDTO cuentaRequestDTO) {

        List<TipoCuenta> listaCuentas = new ArrayList<>();

        for (int i = 0; i < cuentaRequestDTO.getTipoCuenta().size(); i++) {
            TipoCuenta tipoCuenta = TipoCuenta.builder()
                    .cuenta(Cuenta.builder().rut(cuentaRequestDTO.getRut()).build())
                    .tipodeCuenta(cuentaRequestDTO.getTipoCuenta().get(i).getTipodeCuenta())
                    .descripcionCuenta(cuentaRequestDTO.getTipoCuenta().get(i).getDescripcionCuenta())
                    .build();
            listaCuentas.add(tipoCuenta);

        }

        Cuenta cuenta = Cuenta.builder()
                .rut(cuentaRequestDTO.getRut())
                .numeroCuenta(cuentaRequestDTO.getNumeroCuenta())
                .fechaCreacion(LocalDateTime.now().toString())
                .tipoCuenta(listaCuentas)
                .build();

        cuentasRepository.save(cuenta);
    }

    @Override
    public CuentaResponseDTO obtenerCuenta(String id) {
        Optional<Cuenta> oCuenta = cuentasRepository.findById(id);

        if (!oCuenta.isPresent()) {
            throw new ErrorException(HttpStatus.NOT_FOUND, "No se encuentra el registro");
        }

        Cuenta cuenta = oCuenta.get();
        List <TipoCuentaResponseDTO> tipocuentas = cuenta.getTipoCuenta()
                .stream()
                .map(cuentas -> TipoCuentaResponseDTO.builder()
                .idTipoCuenta(cuentas.getIdTipoCuenta())
                .tipoCuenta(cuentas.getTipodeCuenta())
                .descripcionCuenta(cuentas.getDescripcionCuenta())
                        .build())
                        .collect(Collectors.toList());



        return CuentaResponseDTO.builder()
                .rut(oCuenta.get().getRut())
                .NumeroCuenta(oCuenta.get().getNumeroCuenta())
                .tipoCuenta(tipocuentas)
                .build();


    }
}


