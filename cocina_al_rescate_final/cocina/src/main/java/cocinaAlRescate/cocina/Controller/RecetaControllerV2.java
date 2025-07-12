package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.Receta;
import cocinaAlRescate.cocina.assemblers.RecetaModelAssembler;
import cocinaAlRescate.cocina.service.RecetaService;
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
@RequestMapping("/api/v2/receta")
public class RecetaControllerV2 {

   @Autowired
    private RecetaService recetaService;

    @Autowired
    private RecetaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Receta>> getAllRecetas() {
        List<EntityModel<Receta>> recetas = recetaService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(recetas,
                linkTo(methodOn(RecetaControllerV2.class).getAllRecetas()).withSelfRel());
    }

    @GetMapping(value = "/{idReceta}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Receta> getRecetaById(@PathVariable long idReceta) {
        Receta receta = recetaService.buscarporidReceta(idReceta);
        return assembler.toModel(receta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Receta>> createReceta(@RequestBody Receta receta) {
        Receta newReceta = recetaService.guardaReceta(receta);
        return ResponseEntity
                .created(linkTo(methodOn(RecetaControllerV2.class).getRecetaById(newReceta.getIdReceta())).toUri())
                .body(assembler.toModel(newReceta));
    }

    @PutMapping(value = "/{idReceta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Receta>> updateReceta(@PathVariable long idReceta, @RequestBody Receta receta) {
        receta.setIdReceta(idReceta);
        Receta updatedReceta = recetaService.guardaReceta(receta);
        return ResponseEntity
                .ok(assembler.toModel(updatedReceta));
    }

    @DeleteMapping(value = "/{idReceta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteReceta(@PathVariable long idReceta) {
        recetaService.eliminarReceta(idReceta);
        return ResponseEntity.noContent().build();
    }
}
