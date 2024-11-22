package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.ITrabajadorFaenaRepository;
import cl.capstone.ms_gestion_trabajadores.dto.TrabajadorFaenaDTO;
import cl.capstone.ms_gestion_trabajadores.model.TipoCumplimiento;
import cl.capstone.ms_gestion_trabajadores.model.Trabajador;
import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;

@Service
public class TrabajadorFaenaService implements ITrabajadorFaenaService {

    @Autowired
    ITrabajadorFaenaRepository trabajadorFaenaRepository;
    @Autowired
    ITrabajadorService trabajadorService;

    @Override
    public List<TrabajadorFaena> getRegistros() {
        List<TrabajadorFaena> listaRegistros = trabajadorFaenaRepository.findAll();
        return listaRegistros;
    }

    @Override
    public TrabajadorFaenaDTO saveRegistro(TrabajadorFaenaDTO trabajadorFaena) {

        Trabajador trabajador = trabajadorService.findByRun(trabajadorFaena.getRunTrabajador());
        if (trabajador == null) {
            throw new IllegalArgumentException(
                    "El trabajador con RUN " + trabajadorFaena.getRunTrabajador() + " no existe.");
        }

        TrabajadorFaena trabajadorFaena2 = new TrabajadorFaena();
        trabajadorFaena2.setTrabajador(trabajador);
        trabajadorFaena2.setIdFaena(trabajadorFaena.getIdFaena());

        // Asignar TipoCumplimiento con ID 2
        TipoCumplimiento tipoCumplimiento = new TipoCumplimiento();
        tipoCumplimiento.setTipoCumplimiento(2L);
        trabajadorFaena2.setTipoCumplimiento(tipoCumplimiento);

        trabajadorFaenaRepository.save(trabajadorFaena2);
        return trabajadorFaena;
    }

    @Override
    public void deleteRegistro(Long id) {

        trabajadorFaenaRepository.deleteById(id);
    }

    @Override
    public TrabajadorFaena findRegistro(Long id) {

        TrabajadorFaena registro = trabajadorFaenaRepository.findById(id).orElse(null);
        return registro;
    }

    @Override
    public void editCargo(TrabajadorFaenaDTO trabajadorFaena) {

        this.saveRegistro(trabajadorFaena);
    }

}
