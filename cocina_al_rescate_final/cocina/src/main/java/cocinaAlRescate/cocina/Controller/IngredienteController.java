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

import cocinaAlRescate.cocina.Model.Ingrediente;
import cocinaAlRescate.cocina.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/ingrediente")
@Tag(name = "Ingredientes", description = "Operaciones relacionadas a los ingredientes")
public class IngredienteController {
@Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los ingredientes", description = "Obtiene una lista de todos los ingredientes disponibles")
    public ResponseEntity<List<Ingrediente>> listar() {
        List<Ingrediente> ingredientes = ingredienteService.list();
        if (ingredientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingredientes);
}
    @PostMapping("/guardar")
    @Operation(summary = "Guardar un ingrediente", description = "Guarda un nuevo ingrediente en el sistema")
    public ResponseEntity<Ingrediente> guardaringrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente nuevoingrediente = ingredienteService.guardaIngrediente(ingrediente);
        return new ResponseEntity<>(nuevoingrediente, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idingrediente}")
    @Operation(summary = "Buscar ingrediente por ID", description = "Busca un ingrediente específico por su ID")
    public ResponseEntity<Ingrediente> buscaringrediente(@RequestParam("idingrediente") int idingrediente) {
        Ingrediente ingrediente = ingredienteService.buscarporidIngrediente(idingrediente);
        if (ingrediente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingrediente);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar un ingrediente", description = "Actualiza un ingrediente existente en el sistema")
    public ResponseEntity<Ingrediente> actualizaringrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente ingredienteActualizado = ingredienteService.guardaIngrediente(ingrediente);
        if (ingredienteActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredienteActualizado);
    }
    @DeleteMapping("/eliminar/{idingrediente}")
    @Operation(summary = "Eliminar un ingrediente", description = "Elimina un ingrediente del sistema por su ID")   
    public ResponseEntity<String> eliminaringrediente(@PathVariable("idingrediente") int idingrediente){
        ingredienteService.eliminarIngrediente(idingrediente);
        return ResponseEntity.ok("ingrediente eliminado");
    }
}
