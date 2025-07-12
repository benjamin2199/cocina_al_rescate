package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.Ingrediente;
import cocinaAlRescate.cocina.assemblers.IngredienteModelAssembler;
import cocinaAlRescate.cocina.service.IngredienteService;
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
@RequestMapping("/api/v2/ingrediente")
public class IngredienteControllerV2 {

   @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private IngredienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Ingrediente>> getAllIngredientes() {
        List<EntityModel<Ingrediente>> ingredientes = ingredienteService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ingredientes,
                linkTo(methodOn(IngredienteControllerV2.class).getAllIngredientes()).withSelfRel());
    }

    @GetMapping(value = "/{idIngrediente}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Ingrediente> getIngredienteById(@PathVariable long idIngrediente) {
        Ingrediente ingrediente = ingredienteService.buscarporidIngrediente(idIngrediente);
        return assembler.toModel(ingrediente);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ingrediente>> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente newIngrediente = ingredienteService.guardaIngrediente(ingrediente);
        return ResponseEntity
                .created(linkTo(methodOn(IngredienteControllerV2.class).getIngredienteById(newIngrediente.getIdIngrediente())).toUri())
                .body(assembler.toModel(newIngrediente));
    }

    @PutMapping(value = "/{idIngrediente}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ingrediente>> updateIngrediente(@PathVariable long idIngrediente, @RequestBody Ingrediente ingrediente) {
        ingrediente.setIdIngrediente(idIngrediente);
        Ingrediente updatedIngrediente = ingredienteService.guardaIngrediente(ingrediente);
        return ResponseEntity
                .ok(assembler.toModel(updatedIngrediente));
    }

    @DeleteMapping(value = "/{idIngrediente}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteIngrediente(@PathVariable long idIngrediente) {
        ingredienteService.eliminarIngrediente(idIngrediente);
        return ResponseEntity.noContent().build();
    }
}
