package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.PreferenciaControllerV2;
import cocinaAlRescate.cocina.Model.Preferencia;

@Component
public class PreferenciaModelAssembler implements RepresentationModelAssembler<Preferencia, EntityModel<Preferencia>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Preferencia> toModel(@org.springframework.lang.NonNull Preferencia preferencia) {
        return EntityModel.of(preferencia,
                linkTo(methodOn(PreferenciaControllerV2.class).getPreferenciaById((long) preferencia.getIdPreferencia())).withSelfRel(),
                linkTo(methodOn(PreferenciaControllerV2.class).getAllPreferencias()).withRel("preferencias"));
    }
}
