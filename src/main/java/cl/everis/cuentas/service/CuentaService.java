package cl.everis.cuentas.service;

import cl.everis.cuentas.dto.CuentaRequestDTO;
import cl.everis.cuentas.dto.CuentaResponseDTO;

import java.util.List;

public interface CuentaService {
    public List<CuentaResponseDTO> findAll();
    void insertarCuenta(CuentaRequestDTO cuentaRequestDTO);
    CuentaResponseDTO obtenerCuenta(String id);
}
