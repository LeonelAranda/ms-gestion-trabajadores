package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.ITrabajadorRepository;
import cl.capstone.ms_gestion_trabajadores.model.Trabajador;

@Service
public class TrabajadorService implements ITrabajadorService {

    @Autowired
    ITrabajadorRepository trabajadorRepository;

    @Override
    public List<Trabajador> getTrabajadores() {

        List<Trabajador> listaTrabajadores = trabajadorRepository.findAll();
        return listaTrabajadores;
    }

    @Override
    public void saveTrabajador(Trabajador trabajador) {

        trabajadorRepository.save(trabajador);
    }

    @Override
    public void deleteTrabajador(Long id) {

        trabajadorRepository.deleteById(id);
    }

    @Override
    public Trabajador findTrabajador(Long id) {

        Trabajador trabajador = trabajadorRepository.findById(id).orElse(null);
        return trabajador;
    }

    @Override
    public void editTrabajador(Long id, String nuevoNombre, String nuevoApellido) {

        Trabajador trabajador = this.findTrabajador(id);
        trabajador.setPrimero_nombre(nuevoNombre);
        trabajador.setPrimer_apellido(nuevoApellido);

        this.saveTrabajador(trabajador);
    }

    @Override
    public void editTrabajador(Trabajador trabajador) {

        this.saveTrabajador(trabajador);
    }

}
