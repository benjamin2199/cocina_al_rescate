package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.RecetaControllerV2;
import cocinaAlRescate.cocina.Model.Receta;

@Component
public class RecetaModelAssembler implements RepresentationModelAssembler<Receta, EntityModel<Receta>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Receta> toModel(@org.springframework.lang.NonNull Receta receta) {
        return EntityModel.of(receta,
                linkTo(methodOn(RecetaControllerV2.class).getRecetaById((long) receta.getIdReceta())).withSelfRel(),
                linkTo(methodOn(RecetaControllerV2.class).getAllRecetas()).withRel("recetas"));
    }
}