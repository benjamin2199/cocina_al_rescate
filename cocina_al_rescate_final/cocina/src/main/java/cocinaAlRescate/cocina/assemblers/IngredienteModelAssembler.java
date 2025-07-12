package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.IngredienteControllerV2;
import cocinaAlRescate.cocina.Model.Ingrediente;

@Component
public class IngredienteModelAssembler implements RepresentationModelAssembler<Ingrediente, EntityModel<Ingrediente>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Ingrediente> toModel(@org.springframework.lang.NonNull Ingrediente ingrediente) {
        return EntityModel.of(ingrediente,
                linkTo(methodOn(IngredienteControllerV2.class).getIngredienteById((long) ingrediente.getIdIngrediente())).withSelfRel(),
                linkTo(methodOn(IngredienteControllerV2.class).getAllIngredientes()).withRel("ingredientes"));
    }
}