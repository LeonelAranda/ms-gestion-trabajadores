package cl.capstone.ms_gestion_trabajadores.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_trabajadores.dto.RegistroDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroFiltrosDTO;
import cl.capstone.ms_gestion_trabajadores.service.RegistroService;

@RestController
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
    @PostMapping
    public ResponseEntity<List<RegistroDTO>> obtenerRegistros(@RequestBody RegistroFiltrosDTO filtros) {
        List<RegistroDTO> registros = registroService.obtenerRegistrosFiltrados(filtros);
        return ResponseEntity.ok(registros);
    }
}
