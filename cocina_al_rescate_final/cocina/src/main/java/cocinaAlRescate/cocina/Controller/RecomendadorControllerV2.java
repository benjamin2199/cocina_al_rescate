package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.Recomendador;
import cocinaAlRescate.cocina.assemblers.RecomendadorModelAssembler;
import cocinaAlRescate.cocina.service.RecomendadorService;
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
@RequestMapping("/api/v2/Recomendador")
public class RecomendadorControllerV2 {

   @Autowired
    private RecomendadorService recomendadorService;

    @Autowired
    private RecomendadorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Recomendador>> getAllRecomendadors() {
        List<EntityModel<Recomendador>> recomendadors = recomendadorService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(recomendadors,
                linkTo(methodOn(RecomendadorControllerV2.class).getAllRecomendadors()).withSelfRel());
    }

    @GetMapping(value = "/{idRecomendador}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Recomendador> getRecomendadorById(@PathVariable long idRecomendador) {
        Recomendador recomendador = recomendadorService.buscarporidRecomendador(idRecomendador);
        return assembler.toModel(recomendador);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Recomendador>> createRecomendador(@RequestBody Recomendador recomendador) {
        Recomendador newRecomendador = recomendadorService.guardaRecomendador(recomendador);
        return ResponseEntity
                .created(linkTo(methodOn(RecomendadorControllerV2.class).getRecomendadorById(newRecomendador.getIdRecomendador())).toUri())
                .body(assembler.toModel(newRecomendador));
    }

    @PutMapping(value = "/{idRecomendador}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Recomendador>> updateRecomendador(@PathVariable long idRecomendador, @RequestBody Recomendador recomendador) {
        recomendador.setIdRecomendador(idRecomendador);
        Recomendador updatedRecomendador = recomendadorService.guardaRecomendador(recomendador);
        return ResponseEntity
                .ok(assembler.toModel(updatedRecomendador));
    }

    @DeleteMapping(value = "/{idRecomendador}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteRecomendador(@PathVariable long idRecomendador) {
        recomendadorService.eliminarRecomendador(idRecomendador);
        return ResponseEntity.noContent().build();
    }
}
