package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.Preferencia;
import cocinaAlRescate.cocina.assemblers.PreferenciaModelAssembler;
import cocinaAlRescate.cocina.service.PreferenciaService;
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
@RequestMapping("/api/v2/Preferencia")
public class PreferenciaControllerV2 {

   @Autowired
    private PreferenciaService preferenciaService;

    @Autowired
    private PreferenciaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Preferencia>> getAllPreferencias() {
        List<EntityModel<Preferencia>> Preferencias = preferenciaService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(Preferencias,
                linkTo(methodOn(PreferenciaControllerV2.class).getAllPreferencias()).withSelfRel());
    }

    @GetMapping(value = "/{idPreferencia}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Preferencia> getPreferenciaById(@PathVariable long idPreferencia) {
        Preferencia preferencia = preferenciaService.buscarporidPreferencia(idPreferencia);
        return assembler.toModel(preferencia);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Preferencia>> createPreferencia(@RequestBody Preferencia preferencia) {
        Preferencia newPreferencia = preferenciaService.guardaPreferencia(preferencia);
        return ResponseEntity
                .created(linkTo(methodOn(PreferenciaControllerV2.class).getPreferenciaById(newPreferencia.getIdPreferencia())).toUri())
                .body(assembler.toModel(newPreferencia));
    }

    @PutMapping(value = "/{idPreferencia}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Preferencia>> updatePreferencia(@PathVariable long idPreferencia, @RequestBody Preferencia preferencia) {
        preferencia.setIdPreferencia(idPreferencia);
        Preferencia updatedPreferencia = preferenciaService.guardaPreferencia(preferencia);
        return ResponseEntity
                .ok(assembler.toModel(updatedPreferencia));
    }

    @DeleteMapping(value = "/{idPreferencia}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePreferencia(@PathVariable long idPreferencia) {
        preferenciaService.eliminarPreferencia(idPreferencia);
        return ResponseEntity.noContent().build();
    }
}

