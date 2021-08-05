package cl.everis.cuentas.controller;



import cl.everis.cuentas.dto.CuentaRequestDTO;
import cl.everis.cuentas.dto.CuentaResponseDTO;
import cl.everis.cuentas.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;


    @GetMapping
    public ResponseEntity<List<CuentaResponseDTO>> findAll(){
        return new ResponseEntity<>(this.cuentaService.findAll(), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CuentaResponseDTO> leer(@Validated @PathVariable(value = "id") String cuentaId){
        return new ResponseEntity<>(cuentaService.obtenerCuenta(cuentaId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertar (@Valid @RequestBody CuentaRequestDTO cuentaRequestDTO){
        this.cuentaService.insertarCuenta(cuentaRequestDTO);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
