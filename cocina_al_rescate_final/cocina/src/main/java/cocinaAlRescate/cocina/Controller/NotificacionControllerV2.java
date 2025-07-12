package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.Notificacion;
import cocinaAlRescate.cocina.assemblers.NotificacionModelAssembler;
import cocinaAlRescate.cocina.service.NotIficacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/Notificacion")
public class NotificacionControllerV2 {

   @Autowired
    private NotIficacionService notificacionService;

    @Autowired
    private NotificacionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Notificacion>> getAllNotificacions() {
        List<EntityModel<Notificacion>> Notificacions = notificacionService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(Notificacions,
                linkTo(methodOn(NotificacionControllerV2.class).getAllNotificacions()).withSelfRel());
    }

    @GetMapping(value = "/{idNotificacion}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Notificacion> getNotificacionById(@PathVariable long idNotificacion) {
        Notificacion notificacion = notificacionService.buscarporidNotificacion(idNotificacion);
        return assembler.toModel(notificacion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Notificacion>> createNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion newNotificacion = notificacionService.guardarNotificacion(notificacion);
        return ResponseEntity
                .created(linkTo(methodOn(NotificacionControllerV2.class).getNotificacionById(newNotificacion.getIdNotificacion())).toUri())
                .body(assembler.toModel(newNotificacion));
    }

    @PutMapping(value = "/{idNotificacion}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Notificacion>> updateNotificacion(@PathVariable long idNotificacion, @RequestBody Notificacion notificacion) {
        notificacion.setIdNotificacion(idNotificacion);
        Notificacion updatedNotificacion = notificacionService.guardarNotificacion(notificacion);
        return ResponseEntity
                .ok(assembler.toModel(updatedNotificacion));
    }

    @DeleteMapping(value = "/{idNotificacion}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteNotificacion(@PathVariable long idNotificacion) {
        notificacionService.eliminarNotificacion(idNotificacion);
        return ResponseEntity.noContent().build();
    }
}
