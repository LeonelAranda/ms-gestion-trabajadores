package cl.capstone.ms_gestion_trabajadores.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.capstone.ms_gestion_trabajadores.model.Cargo;
import cl.capstone.ms_gestion_trabajadores.model.Response;
import cl.capstone.ms_gestion_trabajadores.service.ICargoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS para todos los endpoints en esta clase
public class CargoController {

    @Autowired
    private ICargoService iCargoService;

    @GetMapping("/cargos/traer")
    public ResponseEntity<Response> getTrabajadores() {

        List<Cargo> cargos = iCargoService.getCargos();
        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        if (cargos == null || cargos.isEmpty()) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Cargos no encotrados.");
            response.setResultado(cargos);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Cargos encontrados.");
            response.setResultado(cargos);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PostMapping("/cargos/crear")
    public ResponseEntity<Response> saveCargo(@RequestBody Cargo cargo) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Cargo nuevocargo = iCargoService.saveCargo(cargo);

        if (nuevocargo != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Cargo creado.");
            response.setResultado(nuevocargo);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se pudo crear el cargo.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cargos/borrar/{id}")
    public ResponseEntity<Response> deleteTrabajador(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Cargo cargo = iCargoService.findCargo(id);

        if (cargo != null) {
            // Si existe el trabajador, procedemos a eliminarlo
            iCargoService.deleteCargo(id);
            response.setCodigoRetorno(0); // Código de éxito
            response.setGlosaRetorno("Cargo eliminado.");
            response.setResultado(cargo);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Si el trabajador no existe, retornamos un mensaje de error
            response.setCodigoRetorno(-1); // Código de error
            response.setGlosaRetorno("No se encontró el cargo.");
            response.setResultado(null);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cargos/traer/{id}")
    public ResponseEntity<Response> trabajador(@PathVariable Long id) {

        Response response = new Response();
        LocalDateTime currentDate = LocalDateTime.now();

        Cargo cargo = iCargoService.findCargo(id);

        if (cargo == null) {
            response.setCodigoRetorno(-1);
            response.setGlosaRetorno("Cargo no encotrado.");
            response.setResultado(cargo);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setCodigoRetorno(0);
            response.setGlosaRetorno("Cargo encontrado.");
            response.setResultado(cargo);
            response.setTimestamp(currentDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/cargos/editar/{id}")
    public Cargo editCargo(@PathVariable Long id,
            @RequestParam(required = false, name = "cargo") String nuevoCargo,
            @RequestParam(required = false, name = "descripcion") String nuevaDescripcion) {

        // Response response = new Response();
        // LocalDateTime currentDate = LocalDateTime.now();

        iCargoService.editCargo(id, nuevoCargo, nuevaDescripcion);

        Cargo cargo = iCargoService.findCargo(id);

        return cargo;

    }

}
