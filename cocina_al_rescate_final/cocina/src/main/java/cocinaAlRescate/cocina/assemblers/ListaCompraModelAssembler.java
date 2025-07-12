package cocinaAlRescate.cocina.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Controller.ListaCompraControllerV2;
import cocinaAlRescate.cocina.Model.ListaCompra;

@Component
public class ListaCompraModelAssembler implements RepresentationModelAssembler<ListaCompra, EntityModel<ListaCompra>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<ListaCompra> toModel(@org.springframework.lang.NonNull ListaCompra listaCompra) {
        return EntityModel.of(listaCompra,
                linkTo(methodOn(ListaCompraControllerV2.class).getListaCompraById((long) listaCompra.getIdListaCompra())).withSelfRel(),
                linkTo(methodOn(ListaCompraControllerV2.class).getAllListaCompras()).withRel("ListaCompras"));
    }
}
