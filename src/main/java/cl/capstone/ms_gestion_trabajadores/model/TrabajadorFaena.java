package cl.capstone.ms_gestion_trabajadores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "TRAB_FAENA")
public class TrabajadorFaena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRAB_FAENA")
    private Long idTrabajadorFaena;

    @Column(name = "ID_FAENA", nullable = false)
    private Long idFaena;

    // Relación @ManyToOne con Trabajador
    @ManyToOne(optional = true)
    @JoinColumn(name = "RUN_TRABAJADOR", referencedColumnName = "run", columnDefinition = "VARCHAR2(20)")
    private Trabajador trabajador;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ID_TIPO_CUMPLIMIENTO", referencedColumnName = "ID_TIPO_CUMPLIMIENTO")
    private TipoCumplimiento tipoCumplimiento;

}
