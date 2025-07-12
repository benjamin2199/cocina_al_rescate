package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.ListaCompra;
import cocinaAlRescate.cocina.assemblers.ListaCompraModelAssembler;
import cocinaAlRescate.cocina.service.ListaCompraService;
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
@RequestMapping("/api/v2/listaCompra")
public class ListaCompraControllerV2 {

   @Autowired
    private ListaCompraService listaCompraService;

    @Autowired
    private ListaCompraModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ListaCompra>> getAllListaCompras() {
        List<EntityModel<ListaCompra>> listaCompras = listaCompraService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(listaCompras,
                linkTo(methodOn(ListaCompraControllerV2.class).getAllListaCompras()).withSelfRel());
    }

    @GetMapping(value = "/{idListaCompra}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<ListaCompra> getListaCompraById(@PathVariable long idListaCompra) {
        ListaCompra listaCompra = listaCompraService.buscarporidListaCompra(idListaCompra);
        return assembler.toModel(listaCompra);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ListaCompra>> createListaCompra(@RequestBody ListaCompra listaCompra) {
        ListaCompra newListaCompra = listaCompraService.guardaListaCompra(listaCompra);
        return ResponseEntity
                .created(linkTo(methodOn(ListaCompraControllerV2.class).getListaCompraById(newListaCompra.getIdListaCompra())).toUri())
                .body(assembler.toModel(newListaCompra));
    }

    @PutMapping(value = "/{idListaCompra}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ListaCompra>> updateListaCompra(@PathVariable long idListaCompra, @RequestBody ListaCompra listaCompra) {
        listaCompra.setIdListaCompra(idListaCompra);
        ListaCompra updatedListaCompra = listaCompraService.guardaListaCompra(listaCompra);
        return ResponseEntity
                .ok(assembler.toModel(updatedListaCompra));
    }

    @DeleteMapping(value = "/{idListaCompra}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteListaCompra(@PathVariable long idListaCompra) {
        listaCompraService.eliminarListaCompra(idListaCompra);
        return ResponseEntity.noContent().build();
    }
}

