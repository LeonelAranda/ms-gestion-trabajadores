package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.RegistroRepository;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroAprobadosDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroFiltrosDTO;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<RegistroDTO> obtenerRegistrosFiltrados(RegistroFiltrosDTO filtro) {
        return registroRepository.obtenerRegistros(filtro);
    }

    public List<RegistroAprobadosDTO> obtenerRegistrosAprobadosFiltrados(RegistroFiltrosDTO filtro, int idFaena) {
        return registroRepository.obtenerRegistrosAprobados(filtro, idFaena);
    }
}
