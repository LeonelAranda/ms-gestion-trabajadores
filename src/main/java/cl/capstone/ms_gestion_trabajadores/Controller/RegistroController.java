package cl.capstone.ms_gestion_trabajadores.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_trabajadores.dto.RegistroAprobadosDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroAprobadosDTO2;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroFiltrosDTO;
import cl.capstone.ms_gestion_trabajadores.model.AlojamientoTransporte;
import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;
import cl.capstone.ms_gestion_trabajadores.service.IAlojamientoTransporteService;
import cl.capstone.ms_gestion_trabajadores.service.RegistroService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS para todos los endpoints en esta clase
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    IAlojamientoTransporteService alojamientoTransporteService;

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

    @PostMapping("/registro/traerAprobados2/{idFaena}")
    public ResponseEntity<List<RegistroAprobadosDTO2>> obtenerRegistrosAprobados2(
            @RequestBody RegistroFiltrosDTO filtros,
            int idFaena) {
        List<RegistroAprobadosDTO2> registros = registroService.obtenerRegistrosAprobadosFiltrados2(filtros, idFaena);
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

    @GetMapping("/actualizar")
    public ResponseEntity<TrabajadorFaena> actualizarTipoCumplimientoCorreo(
            @RequestParam String run,
            @RequestParam Long idFaena,
            @RequestParam Long nuevoTipoCumplimientoId,
            HttpServletResponse response) {

        // Llamamos al servicio para actualizar el tipo de cumplimiento
        TrabajadorFaena trabajadorFaenaActualizado = registroService.actualizarCampoPorRunYFaena(run, idFaena,
                nuevoTipoCumplimientoId);

        // Si no se encuentra el registro, retornamos un error
        if (trabajadorFaenaActualizado == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Si se actualiza correctamente, devolvemos el registro actualizado
        if (nuevoTipoCumplimientoId == 3) {
            try {
                response.sendRedirect("https://sistemagf.cl/aceptado"); // Redirige si aceptó
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else if (nuevoTipoCumplimientoId == 41) {
            try {
                response.sendRedirect("https://sistemagf.cl/rechazado"); // Redirige si rechazó
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.ok(trabajadorFaenaActualizado); // Devolvemos la respuesta exitosa
    }

    @PostMapping("/registro/Alojamientotransporte")
    public ResponseEntity<AlojamientoTransporte> saveAlojamientoTransporte(
            AlojamientoTransporte alojamientoTransporte) {
        AlojamientoTransporte respuesta = alojamientoTransporteService.saveAlojamientoTransporte(alojamientoTransporte);
        return ResponseEntity.ok(respuesta);
    }

}
