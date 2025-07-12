package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.MenuSemanalControllerV2;
import cocinaAlRescate.cocina.Model.MenuSemanal;

@Component
public class MenuSemanalModelAssembler implements RepresentationModelAssembler<MenuSemanal, EntityModel<MenuSemanal>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<MenuSemanal> toModel(@org.springframework.lang.NonNull MenuSemanal menuSemanal) {
        return EntityModel.of(menuSemanal,
                linkTo(methodOn(MenuSemanalControllerV2.class).getMenuSemanalById((long) menuSemanal.getIdMenuSemanal())).withSelfRel(),
                linkTo(methodOn(MenuSemanalControllerV2.class).getAllMenuSemanals()).withRel("menuSemanals"));
    }
}
