package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import cl.capstone.ms_gestion_trabajadores.dto.TrabajadorDTO;
import cl.capstone.ms_gestion_trabajadores.model.Trabajador;

public interface ITrabajadorService {

    public List<Trabajador> getTrabajadores();

    public Trabajador saveTrabajador(Trabajador trabajador);

    public void deleteTrabajador(Long id);

    public Trabajador findTrabajador(Long id);

    public void editTrabajador(Trabajador trabajador);

    public TrabajadorDTO findByPrimerNombre(String nombre);

    public String findByPrimerApellido(String apellido);

    public Trabajador findByComunaAndPrimerApellido(String comuna, String apellido);

    public Trabajador findByRun(String run);

}
