package cocinaAlRescate.cocina.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cocinaAlRescate.cocina.Model.Usuario;
import cocinaAlRescate.cocina.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuarios", description = "Operaciones relacionadas a los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios")
     @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)))
    })
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.list();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Guardar un usuario", description = "Guarda un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)))
    })
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardaUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Buscar usuario por ID", description = "Busca un usuario específico por su ID")
    public ResponseEntity<Usuario> buscarUsuario(@RequestParam("idUsuario") int idUsuario) {
        Usuario usuario = usuarioService.buscarporidUsuario(idUsuario);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza la información de un usuario existente")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.guardaUsuario(usuario);
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioActualizado);
    }
    @DeleteMapping("/eliminar/{idUsuario}")
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema por su ID")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("idUsuario") int idUsuario){
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Usuario eliminado");
    }




}
