package cl.capstone.ms_gestion_trabajadores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class RegistroAprobadosDTO {

    private Long idFaena;
    private String primerNombre;
    private String primerApellido;
    private String run;
    private String email;
    private String nombreCargo;
    private String nombreCumplimiento;
}
