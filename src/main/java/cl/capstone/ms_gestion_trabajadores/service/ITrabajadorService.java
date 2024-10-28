package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;
import cl.capstone.ms_gestion_trabajadores.model.Trabajador;

public interface ITrabajadorService {

    public List<Trabajador> getTrabajadores();

    public void saveTrabajador(Trabajador trabajador);

    public void deleteTrabajador(Long id);

    public Trabajador findTrabajador(Long id);

    public void editTrabajador(Long id, String nuevoNombre, String nuevoApellido);

    public void editTrabajador(Trabajador trabajador);

}
