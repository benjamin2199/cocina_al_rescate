package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.MenuSemanal;
import cocinaAlRescate.cocina.assemblers.MenuSemanalModelAssembler;
import cocinaAlRescate.cocina.service.MenuSemanalService;
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
@RequestMapping("/api/v2/menuSemanal")
public class MenuSemanalControllerV2 {

   @Autowired
    private MenuSemanalService menuSemanalService;

    @Autowired
    private MenuSemanalModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<MenuSemanal>> getAllMenuSemanals() {
        List<EntityModel<MenuSemanal>> menuSemanals = menuSemanalService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(menuSemanals,
                linkTo(methodOn(MenuSemanalControllerV2.class).getAllMenuSemanals()).withSelfRel());
    }

    @GetMapping(value = "/{idMenuSemanal}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<MenuSemanal> getMenuSemanalById(@PathVariable long idMenuSemanal) {
        MenuSemanal menuSemanal = menuSemanalService.buscarporidMenuSemanal(idMenuSemanal);
        return assembler.toModel(menuSemanal);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MenuSemanal>> createMenuSemanal(@RequestBody MenuSemanal menuSemanal) {
        MenuSemanal newMenuSemanal = menuSemanalService.guardaMenuSemanal(menuSemanal);
        return ResponseEntity
                .created(linkTo(methodOn(MenuSemanalControllerV2.class).getMenuSemanalById(newMenuSemanal.getIdMenuSemanal())).toUri())
                .body(assembler.toModel(newMenuSemanal));
    }

    @PutMapping(value = "/{idMenuSemanal}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MenuSemanal>> updateMenuSemanal(@PathVariable long idMenuSemanal, @RequestBody MenuSemanal menuSemanal) {
        menuSemanal.setIdMenuSemanal(idMenuSemanal);
        MenuSemanal updatedMenuSemanal = menuSemanalService.guardaMenuSemanal(menuSemanal);
        return ResponseEntity
                .ok(assembler.toModel(updatedMenuSemanal));
    }

    @DeleteMapping(value = "/{idMenuSemanal}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteMenuSemanal(@PathVariable long idMenuSemanal) {
        menuSemanalService.eliminarMenuSemanal(idMenuSemanal);
        return ResponseEntity.noContent().build();
    }
}

