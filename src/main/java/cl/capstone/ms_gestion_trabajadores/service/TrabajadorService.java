package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.ITrabajadorRepository;
import cl.capstone.ms_gestion_trabajadores.dto.TrabajadorDTO;
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
    public Trabajador saveTrabajador(Trabajador trabajador) {

        trabajadorRepository.save(trabajador);

        return trabajador;
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
    public void editTrabajador(Trabajador trabajador) {

        this.saveTrabajador(trabajador);
    }

    public TrabajadorDTO findByPrimerNombre(String nombre) {

        TrabajadorDTO trabajadorDTO = new TrabajadorDTO();

        Trabajador trabajador = trabajadorRepository.findByPrimerNombre(nombre);
        trabajadorDTO.setIdTrabajador(trabajador.getIdTrabajador());
        trabajadorDTO.setPrimerNombre(trabajador.getPrimerNombre());
        trabajadorDTO.setPrimerApellido(trabajador.getPrimerApellido());

        return trabajadorDTO;

    }

    @Override
    public String findByPrimerApellido(String apellido) {

        return trabajadorRepository.findByPrimerApellido(apellido);
    }

    @Override
    public Trabajador findByComunaAndPrimerApellido(String comuna, String apellido) {

        return trabajadorRepository.findByComunaAndPrimerApellido(comuna, apellido);
    }

    public Trabajador findByRun(String run) {

        return trabajadorRepository.findByRun(run);
    }

}
