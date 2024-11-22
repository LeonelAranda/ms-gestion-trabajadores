package cl.capstone.ms_gestion_trabajadores.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_trabajadores.dto.TrabajadorFaenaDTO;
import cl.capstone.ms_gestion_trabajadores.model.Response;
import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;
import cl.capstone.ms_gestion_trabajadores.service.ITrabajadorFaenaService;

@RestController

public class TrabajadorFaenaController {

    @Autowired
    ITrabajadorFaenaService trabajadorFaenaService;

    @PostMapping("/registro/crear")
    public ResponseEntity<Response> saveRegistro(@RequestBody TrabajadorFaenaDTO trabajadorFaena) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        TrabajadorFaenaDTO nuevoRegistro = trabajadorFaenaService
                .saveRegistro(trabajadorFaena);

        // Verificar si se guardó correctamente
        if (nuevoRegistro != null) {
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Registro creado exitosamente.");
            response.setResultado(nuevoRegistro);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            // En caso de fallo al guardar el TipoCumplimiento
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("Error al crear el registro.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/registro/borrar/{id}")
    public ResponseEntity<Response> deleteRegistro(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        TrabajadorFaena registro = trabajadorFaenaService.findRegistro(id);

        if (registro != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            trabajadorFaenaService.deleteRegistro(id);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Registro eliminado.");
            response.setResultado(registro);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el registro.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/registro/editar")
    public ResponseEntity<Response> editRegistro(@RequestBody TrabajadorFaenaDTO trabajadorFaena) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        TrabajadorFaena encontrarRegistro = trabajadorFaenaService.findRegistro(trabajadorFaena.getIdFaena());

        if (encontrarRegistro != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            trabajadorFaenaService.editCargo(trabajadorFaena);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Registro modificado.");
            response.setResultado(encontrarRegistro);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el registro.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/registro/traer")
    public ResponseEntity<Response> getRegistros() {

        List<TrabajadorFaena> listaRegistros = trabajadorFaenaService.getRegistros();
        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        if (listaRegistros == null || listaRegistros.isEmpty()) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Registros no encotrados.");
            response.setResultado(listaRegistros);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Registros encontrados.");
            response.setResultado(listaRegistros);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
}
