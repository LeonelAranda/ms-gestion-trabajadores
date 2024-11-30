package cl.capstone.ms_gestion_trabajadores.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_trabajadores.dto.RegistroAprobadosDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroFiltrosDTO;
import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;
import cl.capstone.ms_gestion_trabajadores.service.RegistroService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS para todos los endpoints en esta clase
@RequestMapping("/api/registros")
public class RegistroController {

    private final RegistroService registroService;

    @Autowired
    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    /**
     * Endpoint para obtener registros filtrados.
     * 
     * @param filtros DTO con los criterios de filtro.
     * @return Lista de registros que cumplen los criterios.
     */
    @PostMapping("/registro/traer")
    public ResponseEntity<List<RegistroDTO>> obtenerRegistros(@RequestBody RegistroFiltrosDTO filtros) {
        List<RegistroDTO> registros = registroService.obtenerRegistrosFiltrados(filtros);
        return ResponseEntity.ok(registros);
    }

    @PostMapping("/registro/traerAprobados/{idFaena}")
    public ResponseEntity<List<RegistroAprobadosDTO>> obtenerRegistrosAprobados(@RequestBody RegistroFiltrosDTO filtros,
            int idFaena) {
        List<RegistroAprobadosDTO> registros = registroService.obtenerRegistrosAprobadosFiltrados(filtros, idFaena);
        return ResponseEntity.ok(registros);
    }

    @PutMapping("/actualizar/{run}/{idFaena}/{nuevoTipoCumplimientoId}")
    public ResponseEntity<TrabajadorFaena> actualizarTipoCumplimiento(
            @PathVariable String run,
            @PathVariable Long idFaena,
            @PathVariable Long nuevoTipoCumplimientoId) {

        // Llamamos al servicio para actualizar el tipo de cumplimiento
        TrabajadorFaena trabajadorFaenaActualizado = registroService.actualizarCampoPorRunYFaena(run, idFaena,
                nuevoTipoCumplimientoId);

        // Si no se encuentra el registro, retornamos un error
        if (trabajadorFaenaActualizado == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Si se actualiza correctamente, devolvemos el registro actualizado
        return ResponseEntity.ok(trabajadorFaenaActualizado);
    }
}
