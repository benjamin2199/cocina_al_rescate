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

import cocinaAlRescate.cocina.Model.MenuSemanal;
import cocinaAlRescate.cocina.service.MenuSemanalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/menusemanal")
@Tag(name= "Menú Semanal", description= "Operaciones relacionadas al menú semanal de recetas ")
public class MenuSemanalController {
@Autowired
    private MenuSemanalService menuSemanalService;

    @GetMapping
    @Operation(summary = "Obtener todos los menús semanales", description = "Obtiene una lista de todos los menús semanales de recetas")
    public ResponseEntity<List<MenuSemanal>> listar() {
        List<MenuSemanal> menuSemanals = menuSemanalService.list();
        if (menuSemanals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menuSemanals);
}
    @PostMapping("/guardar")
    @Operation(summary = "Guardar un menú semanal", description = "Guarda un nuevo menú semanal de recetas en el sistema")
    public ResponseEntity<MenuSemanal> guardarmenuSemanal(@RequestBody MenuSemanal menuSemanal) {
        MenuSemanal nuevomenuSemanal = menuSemanalService.guardaMenuSemanal(menuSemanal);
        return new ResponseEntity<>(nuevomenuSemanal, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idmenuSemanal}")
    @Operation(summary = "Buscar menú semanal por ID", description = "Busca un menú semanal específico por su ID")
    public ResponseEntity<MenuSemanal> buscarmenuSemanal(@RequestParam("idmenuSemanal") int idmenuSemanal) {
        MenuSemanal menuSemanal = menuSemanalService.buscarporidMenuSemanal(idmenuSemanal);
        if (menuSemanal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(menuSemanal);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar un menú semanal", description = "Actualiza un menú semanal existente en el sistema")
    public ResponseEntity<MenuSemanal> actualizarmenuSemanal(@RequestBody MenuSemanal menuSemanal) {
        MenuSemanal menuSemanalActualizado = menuSemanalService.guardaMenuSemanal(menuSemanal);
        if (menuSemanalActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(menuSemanalActualizado);
    }
    @DeleteMapping("/eliminar/{idmenuSemanal}")
    @Operation(summary = "Eliminar un menú semanal", description = "Elimina un menú semanal específico por su ID")  
    public ResponseEntity<String> eliminarmenuSemanal(@PathVariable("idmenuSemanal") int idmenuSemanal){
        menuSemanalService.eliminarMenuSemanal(idmenuSemanal);
        return ResponseEntity.ok("menuSemanal eliminado");
    }
}
