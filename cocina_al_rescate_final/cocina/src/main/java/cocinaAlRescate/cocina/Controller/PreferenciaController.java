package cocinaAlRescate.cocina.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cocinaAlRescate.cocina.Model.Preferencia;
import cocinaAlRescate.cocina.service.PreferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/preferencia")
@Tag(name = "Preferencias", description = "Operaciones relacionadas a las preferencias de los usuarios")    
public class PreferenciaController {
@Autowired
    private PreferenciaService preferenciaService;

    @GetMapping 
    @Operation(summary = "Obtener todas las preferencias", description = "Obtiene una lista de todas las preferencias de los usuarios")
    public ResponseEntity<List<Preferencia>> listar() {
        List<Preferencia> preferencias = preferenciaService.list();
        if (preferencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(preferencias);
}
    @PostMapping("/guardar")
    @Operation(summary = "Guardar una preferencia", description = "Guarda una nueva preferencia de usuario en el sistema")  
    public ResponseEntity<Preferencia> guardarpreferencia(@RequestBody Preferencia preferencia) {
        Preferencia nuevopreferencia = preferenciaService.guardaPreferencia(preferencia);
        return new ResponseEntity<>(nuevopreferencia, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idpreferencia}")
    @Operation(summary = "Buscar preferencia por ID", description = "Busca una preferencia específica por su ID")
    public ResponseEntity<Preferencia> buscarpreferencia(@RequestParam("idpreferencia") int idpreferencia) {
        Preferencia preferencia = preferenciaService.buscarporidPreferencia(idpreferencia);
        if (preferencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(preferencia);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar una preferencia", description = "Actualiza una preferencia existente en el sistema")
    public ResponseEntity<Preferencia> actualizarpreferencia(@RequestBody Preferencia preferencia) {
        Preferencia preferenciaActualizado = preferenciaService.guardaPreferencia(preferencia);
        if (preferenciaActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(preferenciaActualizado);
    }
    @DeleteMapping("/eliminar/{idpreferencia}")
    @Operation(summary = "Eliminar una preferencia", description = "Elimina una preferencia específica por su ID")
    public ResponseEntity<String> eliminarpreferencia(@PathVariable("idpreferencia") int idpreferencia){
        preferenciaService.eliminarPreferencia(idpreferencia);
        return ResponseEntity.ok("preferencia eliminada");
    }
}
