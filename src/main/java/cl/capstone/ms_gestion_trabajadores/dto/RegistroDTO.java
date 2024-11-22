package cl.capstone.ms_gestion_trabajadores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class RegistroDTO {
    private Long idFaena;
    private String nombreFaena;
    private String primerNombre;
    private String primerApellido;
    private String email;
    private String nombreCargo;
    private String nombreCumplimiento;
}
