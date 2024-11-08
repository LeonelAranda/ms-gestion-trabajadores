package cl.capstone.ms_gestion_trabajadores.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_trabajadores.model.Response;
import cl.capstone.ms_gestion_trabajadores.model.TipoCumplimiento;
import cl.capstone.ms_gestion_trabajadores.service.ITipoCumplimientoService;

@RestController
public class TipoCumplimientoController {

    @Autowired
    ITipoCumplimientoService tipoCumplimientoService;

    @PostMapping("/tipocumplimiento/crear")
    public ResponseEntity<Response> saveTipoCumplimiento(@RequestBody TipoCumplimiento tipoCumplimiento) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        TipoCumplimiento nuevTipoCumplimiento = tipoCumplimientoService
                .saveTipoCumplimiento(tipoCumplimiento);

        // Verificar si se guardó correctamente
        if (nuevTipoCumplimiento != null) {
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Tipo de cumplimiento creado exitosamente.");
            response.setResultado(nuevTipoCumplimiento);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            // En caso de fallo al guardar el TipoCumplimiento
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("Error al crear el tipo de cumplimiento.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tipocumplimiento/traer")
    public ResponseEntity<Response> getTrabajadores() {

        List<TipoCumplimiento> listaTipoCumplimiento = tipoCumplimientoService.getTiposCumplimientos();
        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        if (listaTipoCumplimiento == null || listaTipoCumplimiento.isEmpty()) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Lista de cumplimientos no encotrados.");
            response.setResultado(listaTipoCumplimiento);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Lista de cumplimientos encontrados.");
            response.setResultado(listaTipoCumplimiento);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

}
