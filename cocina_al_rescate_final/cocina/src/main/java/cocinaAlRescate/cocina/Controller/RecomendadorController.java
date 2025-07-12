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

import cocinaAlRescate.cocina.Model.Recomendador;
import cocinaAlRescate.cocina.service.RecomendadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/recomendador")
@Tag(name = "Recomendadores", description = "Operaciones relacionadas a los recomendadores")
public class RecomendadorController {

      @Autowired
    private RecomendadorService recomendadorService;

    @GetMapping
    @Operation(summary = "Obtener todos los recomendadores", description = "Obtiene una lista de todos los recomendadores")
    public ResponseEntity<List<Recomendador>> listar() {
        List<Recomendador> recomendado = recomendadorService.list();
        if (recomendado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recomendado);
}
    @PostMapping("/guardar")
    @Operation(summary = "Guardar un recomendador", description = "Guarda un nuevo recomendador en el sistema")
    public ResponseEntity<Recomendador> guardarrecomendador(@RequestBody Recomendador recomendador) {
        Recomendador nuevorecomendador = recomendadorService.guardaRecomendador(recomendador);
        return new ResponseEntity<>(nuevorecomendador, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idrecomendador}")
    @Operation (summary = "Buscar recomendador por ID", description = "Busca un recomendador específico por su ID")
    public ResponseEntity<Recomendador> buscarrecomendador(@RequestParam("idrecomendador") int idrecomendador) {
        Recomendador recomendador = recomendadorService.buscarporidRecomendador(idrecomendador);
        if (recomendador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recomendador);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar un recomendador", description = "Actualiza un recomendador existente en el sistema")
    public ResponseEntity<Recomendador> actualizarecomendador(@RequestBody Recomendador recomendador) {
        Recomendador recomendadorActualizado = recomendadorService.guardaRecomendador(recomendador);
        if (recomendadorActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recomendadorActualizado);
    }
    @DeleteMapping("/eliminar/{idrecomendador}")
    @Operation(summary = "Eliminar un recomendador", description = "Elimina un recomendador del sistema por su ID") 
    public ResponseEntity<String> eliminarrecomendador(@PathVariable("idrecomendador") int idrecomendador){
        recomendadorService.eliminarRecomendador(idrecomendador);
        return ResponseEntity.ok("recomendador eliminado");
    
}
}
