package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.RecomendadorControllerV2;
import cocinaAlRescate.cocina.Model.Recomendador;

@Component
public class RecomendadorModelAssembler implements RepresentationModelAssembler<Recomendador, EntityModel<Recomendador>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<Recomendador> toModel(@org.springframework.lang.NonNull Recomendador recomendador) {
        return EntityModel.of(recomendador,
                linkTo(methodOn(RecomendadorControllerV2.class).getRecomendadorById((long) recomendador.getIdRecomendador())).withSelfRel(),
                linkTo(methodOn(RecomendadorControllerV2.class).getAllRecomendadors()).withRel("recomendadores"));
    }
}