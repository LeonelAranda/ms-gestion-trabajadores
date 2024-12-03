package cl.capstone.ms_gestion_trabajadores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ALOJAMIENTO_TRANSPORTE")
public class AlojamientoTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Alojamiento_Transporte_ID")
    private Long id;

    @Column(name = "RUN_TRABAJADOR", nullable = true)
    private String runTrabajador;

    @Column(name = "ID_FAENA", nullable = true)
    private Long idFaena;

    @Column(name = "HABITACION", nullable = true)
    private int habitacion;

    @Column(name = "HOTEL", nullable = true)
    private String hotel;

    @Column(name = "TRANSPORTE", nullable = true)
    private String transporte;
}
