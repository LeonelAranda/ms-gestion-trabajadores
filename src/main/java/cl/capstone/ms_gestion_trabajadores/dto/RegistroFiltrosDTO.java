package cl.capstone.ms_gestion_trabajadores.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistroFiltrosDTO {

    private Integer tipoCumplimiento;
    private Integer cargo;
    private String rut;
    private Integer faena;
}
