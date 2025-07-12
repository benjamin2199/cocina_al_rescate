package cocinaAlRescate.cocina.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import cocinaAlRescate.cocina.Model.Notificacion;
import cocinaAlRescate.cocina.service.NotIficacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/notificacion")
@Tag(name = "Notificaciones", description = "Operaciones relacionadas a las notificaciones de los usuarios")
public class NotificacionController {
     @Autowired
    private NotIficacionService notificacionService;

    @GetMapping
    @Operation (summary = "Obtener todas las notificaciones", description = "Obtiene una lista de todas las notificaciones")
    public ResponseEntity<List<Notificacion>> listar() {
        List<Notificacion> notificacions = notificacionService.list();
        if (notificacions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notificacions);
}
    @PostMapping("/guardar")
    @Operation(summary = "Guardar una notificación", description = "Guarda una nueva notificación en el sistema")
    public ResponseEntity<Notificacion> guardarnotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevonotificacion = notificacionService.guardarNotificacion(notificacion);
        return new ResponseEntity<>(nuevonotificacion, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idnotificacion}")
    @Operation (summary = "Buscar notificación por ID", description = "Busca una notificación específica por su ID")
    public ResponseEntity<Notificacion> buscarnotificacion(@RequestParam("idnotificacion") int idnotificacion) {
        Notificacion notificacion = notificacionService.buscarporidNotificacion(idnotificacion);
        if (notificacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacion);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar una notificación", description = "Actualiza una notificación existente en el sistema")
    public ResponseEntity<Notificacion> actualizarnotificacion(@RequestBody Notificacion notificacion) {
        Notificacion notificacionActualizado = notificacionService.guardarNotificacion(notificacion);
        if (notificacionActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacionActualizado);
    }
    @DeleteMapping("/eliminar/{idnotificacion}")
    @Operation(summary = "Eliminar una notificación", description = "Elimina una notificación específica por su ID")
    public ResponseEntity<String> eliminarnotificacion(@PathVariable("idnotificacion") int idnotificacion){
        notificacionService.eliminarNotificacion(idnotificacion);
        return ResponseEntity.ok("notificacion eliminado");
    }
}
