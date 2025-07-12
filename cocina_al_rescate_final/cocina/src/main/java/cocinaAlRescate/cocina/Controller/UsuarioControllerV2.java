package cocinaAlRescate.cocina.Controller;


import cocinaAlRescate.cocina.Model.Usuario;
import cocinaAlRescate.cocina.assemblers.UsuarioModelAssembler;
import cocinaAlRescate.cocina.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/usuario")
public class UsuarioControllerV2 {

   @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping(value = "/{idUsuario}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> getUsuarioByidUsuario(@PathVariable long idUsuario) {
        Usuario usuario = usuarioService.buscarporidUsuario(idUsuario);
        return assembler.toModel(usuario);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.guardaUsuario(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByidUsuario(newUsuario.getIdUsuario())).toUri())
                .body(assembler.toModel(newUsuario))
                ;
    }

    @PutMapping(value = "/{idUsuario}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> updateUsuario(@PathVariable long idUsuario, @RequestBody Usuario usuario) {
        usuario.setIdUsuario(idUsuario);
        Usuario updatedUsuario = usuarioService.guardaUsuario(usuario);
        return ResponseEntity
                .ok(assembler.toModel(updatedUsuario));
    }

    @DeleteMapping(value = "/{idUsuario}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteUsuario(@PathVariable long idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Usuario eliminado");
    }
}

