package cl.everis.clientes.service

import cl.everis.cuentas.dto.CuentaRequestDTO
import cl.everis.cuentas.dto.CuentaResponseDTO
import cl.everis.cuentas.exception.ErrorException
import cl.everis.cuentas.model.Cuenta
import cl.everis.cuentas.model.TipoCuenta
import cl.everis.cuentas.repository.CuentasRepository
import cl.everis.cuentas.service.CuentaService
import cl.everis.cuentas.service.CuentaServiceImpl
import spock.lang.Specification


class CuentaServiceSpec extends Specification{

    CuentaService cuentaService
    CuentasRepository cuentasRepository

    Cuenta cuenta;
    Cuenta cuentauno;
    Cuenta cuentados;
    TipoCuenta tipoCuenta;

    List<Cuenta> cuentas = new ArrayList<>()


    def setup(){
        this.cuentasRepository = Stub(CuentasRepository.class)
        List<TipoCuenta> listaTipoCuenta= new ArrayList<>()


        this.tipoCuenta = TipoCuenta.builder()
                            .idTipoCuenta(1)
                            .tipodeCuenta("Credito")
                            .descripcionCuenta("Cuenta Credito en Dolares")
                            .build()

        listaTipoCuenta.add(this.tipoCuenta)
        this.cuenta = Cuenta.builder()
                        .rut("1111111")
                        .numeroCuenta(11112222)
                        .tipoCuenta(listaTipoCuenta)
                        .fechaCreacion("12334321")
                        .build()

        this.cuentaService = new CuentaServiceImpl(this.cuentasRepository)

    }


    def"Listar Cuentas"(){
        given:"datos"
        this.tipoCuenta = TipoCuenta.builder()
                            .idTipoCuenta(1)
                            .tipodeCuenta("Credito")
                            .descripcionCuenta("Cuenta Credito en Dolares")
                            .build()

        List<TipoCuenta> listaTipoCuenta= new ArrayList<>()
        listaTipoCuenta.add(this.tipoCuenta)

        this.cuentauno = Cuenta.builder()
                            .rut("222222")
                            .numeroCuenta(123123123)
                            .tipoCuenta(listaTipoCuenta)
                            .fechaCreacion("111222233")
                            .build()

        this.cuentados = Cuenta.builder()
                            .rut("333333")
                            .numeroCuenta(123123143143)
                            .tipoCuenta(listaTipoCuenta)
                            .fechaCreacion("123123132")
                            .build()

        this.cuentas.add(this.cuentauno)
        this.cuentas.add(this.cuentados)

        this.cuentasRepository.findAll() >> this.cuentas
        when:
            List <CuentaResponseDTO> respuesta = this.cuentaService.findAll()
        then:
        respuesta.size() == 2
        cuentas.get(0).getRut().equals("222222")
        cuentas.get(1).getRut().equals("333333")

    }

    def"Listar Cuentas Vacios"(){
        given:"datos"
        List<Cuenta> cuentas = new ArrayList<>()
        this.cuentasRepository.findAll() >> cuentas

        when:"Listar Cuentas"
        List <CuentaResponseDTO> respuesta = this.cuentaService.findAll()
        then:"Respuesta"
        respuesta.size() == 0
    }

    def"Insertar Usuarios"(){
        given:"datos"
        this.tipoCuenta = TipoCuenta.builder()
                .idTipoCuenta(1)
                .tipodeCuenta("Credito")
                .descripcionCuenta("Cuenta Credito en Dolares")
                .build()

        List<TipoCuenta> listaTipoCuenta= new ArrayList<>()
        listaTipoCuenta.add(this.tipoCuenta)

        CuentaRequestDTO cuentaRequestDTO = CuentaRequestDTO.builder()
            .rut("11123123")
            .numeroCuenta(12312312)
            .tipoCuenta(listaTipoCuenta)
            .fechaCreacion("1123123")
            .build()
        this.cuentasRepository.findById(_) >> Optional.empty()

        when:"Asignar"
        this.cuentaService.insertarCuenta(cuentaRequestDTO)

        then:"comparar"
        noExceptionThrown()
    }

}
