package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.dto.TrabajadorFaenaDTO;
import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;

@Service
public interface ITrabajadorFaenaService {

    public List<TrabajadorFaena> getRegistros();

    public TrabajadorFaenaDTO saveRegistro(TrabajadorFaenaDTO trabajadorFaena);

    public void deleteRegistro(Long id);

    public TrabajadorFaena findRegistro(Long id);

    public void editCargo(TrabajadorFaenaDTO trabajadorFaena);

}
