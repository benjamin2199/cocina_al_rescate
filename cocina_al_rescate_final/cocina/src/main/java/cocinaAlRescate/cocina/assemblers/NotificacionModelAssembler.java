package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.NotificacionControllerV2;
import cocinaAlRescate.cocina.Model.Notificacion;

@Component
public class NotificacionModelAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Notificacion> toModel(@org.springframework.lang.NonNull Notificacion notificacion) {
        return EntityModel.of(notificacion,
                linkTo(methodOn(NotificacionControllerV2.class).getNotificacionById((long) notificacion.getIdNotificacion())).withSelfRel(),
                linkTo(methodOn(NotificacionControllerV2.class).getAllNotificacions()).withRel("notificaciones"));
    }
}