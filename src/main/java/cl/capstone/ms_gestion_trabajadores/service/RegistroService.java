package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.IRegistroRepository;
import cl.capstone.ms_gestion_trabajadores.Repository.ITipoCumplimientoRepository;
import cl.capstone.ms_gestion_trabajadores.Repository.RegistroRepository;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroAprobadosDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroFiltrosDTO;
import cl.capstone.ms_gestion_trabajadores.model.TipoCumplimiento;
import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private IRegistroRepository iregistroRepository;

    @Autowired
    private ITipoCumplimientoRepository tipoCumplimientoRepository;

    public List<RegistroDTO> obtenerRegistrosFiltrados(RegistroFiltrosDTO filtro) {
        return registroRepository.obtenerRegistros(filtro);
    }

    public List<RegistroAprobadosDTO> obtenerRegistrosAprobadosFiltrados(RegistroFiltrosDTO filtro, int idFaena) {
        return registroRepository.obtenerRegistrosAprobados(filtro, idFaena);
    }

    public TrabajadorFaena actualizarCampoPorRunYFaena(String run, Long idFaena, Long nuevoTipoCumplimientoId) {
        // Buscar el registro por run y faena
        Optional<TrabajadorFaena> optionalTrabajadorFaena = iregistroRepository.findByTrabajadorRunAndIdFaena(run,
                idFaena);

        // Verificar si el registro existe
        if (optionalTrabajadorFaena.isPresent()) {
            TrabajadorFaena trabajadorFaena = optionalTrabajadorFaena.get();

            // Buscar el TipoCumplimiento con el ID proporcionado (nuevoTipoCumplimientoId)
            Optional<TipoCumplimiento> tipoCumplimientoOptional = tipoCumplimientoRepository
                    .findById(nuevoTipoCumplimientoId);

            if (tipoCumplimientoOptional.isPresent()) {
                // Modificar el campo tipoCumplimiento
                trabajadorFaena.setTipoCumplimiento(tipoCumplimientoOptional.get());

                // Guardar el registro actualizado
                return iregistroRepository.save(trabajadorFaena);
            } else {
                throw new IllegalArgumentException(
                        "TipoCumplimiento con ID " + nuevoTipoCumplimientoId + " no encontrado.");
            }
        }

        // Si no se encuentra el registro, retornar null o manejar según lo desees
        return null;
    }

}
