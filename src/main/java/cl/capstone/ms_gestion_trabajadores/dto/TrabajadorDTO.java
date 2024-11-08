package cl.capstone.ms_gestion_trabajadores.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TrabajadorDTO {

    private Long idTrabajador;

    private Long idTipoUsuario;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String run;

    private LocalDate fechaNacimiento;

    private LocalDate fechaContratacion;

    private String comuna;

    private String region;

    private String calleDireccion;

    private String numeroDireccion;

    private String telefono;

    private String email;

    private int cargoId;
}
