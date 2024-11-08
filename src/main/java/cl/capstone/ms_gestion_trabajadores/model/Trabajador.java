package cl.capstone.ms_gestion_trabajadores.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TRABAJADOR")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrabajador;

    @Column(nullable = false)
    private Long idTipoUsuario;

    @Column(nullable = false)
    private String primerNombre;

    @Column(nullable = false)
    private String segundoNombre;

    @Column(nullable = false)
    private String primerApellido;

    @Column(nullable = false)
    private String segundoApellido;

    @Column(nullable = false)
    private String run;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String comuna;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "CARGO_ID", referencedColumnName = "CARGO_ID")
    private Cargo cargos;

}
