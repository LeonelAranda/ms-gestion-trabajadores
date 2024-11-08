package cl.capstone.ms_gestion_trabajadores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "TIPO_CUMPLIMIENTO")
public class TipoCumplimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_CUMPLIMIENTO")
    private Long tipoCumplimiento;

    @Column(name = "NOMBRE_CUMPLIMIENTO")
    private String nombreCumplimiento;

    @Column(name = "DESCRIPCION")
    private String descripcion;

}
