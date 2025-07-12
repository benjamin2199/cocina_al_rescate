package cocinaAlRescate.cocina.Controller;

import cocinaAlRescate.cocina.service.UsuarioService;
import cocinaAlRescate.cocina.Model.Usuario;
import cocinaAlRescate.cocina.assemblers.UsuarioModelAssembler;

import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import java.util.List;

@WebMvcTest(UsuarioControllerV2.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioModelAssembler assembler;

 @Test
    public void metodoGetAllUsuarios() throws Exception {
        Usuario usuario = crearUsuario();
        EntityModel<Usuario> usuarioModel = EntityModel.of(usuario);

        Mockito.when(usuarioService.list()).thenReturn(List.of(usuario));
        Mockito.when(assembler.toModel(usuario)).thenReturn(usuarioModel);

        mockMvc.perform(get("/api/v2/usuario")
                        .accept("application/hal+json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }
    @Test
    public void metodoGetUsuarioById() throws Exception {
        Usuario usuario = crearUsuario();
        EntityModel<Usuario> usuarioModel = EntityModel.of(usuario);

        Mockito.when(usuarioService.buscarporidUsuario(11)).thenReturn(usuario);
        Mockito.when(assembler.toModel(usuario)).thenReturn(usuarioModel);

        mockMvc.perform(get("/api/v2/usuario/11")
                        .accept("application/hal+json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }
    @Test
    public void metodoPostV2() throws Exception {
        Usuario usuario = crearUsuario();
        EntityModel<Usuario> usuarioModel = EntityModel.of(usuario);

        Mockito.when(usuarioService.guardaUsuario(any(Usuario.class))).thenReturn(usuario);
        Mockito.when(assembler.toModel(usuario)).thenReturn(usuarioModel);

        mockMvc.perform(post("/api/v2/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "rutUsuario": "12345678-9",
                                  "nombreUsuario": "Juan",
                                  "apellidoUsuario": "Pérez",
                                  "edad": 30,
                                  "correo": "juan.perez@example.com"
                                }
                                """)
                        .accept("application/hal+json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/hal+json"));
    }
    @Test
    public void metodoPutV2() throws Exception {
        Usuario usuario = crearUsuario();
        EntityModel<Usuario> usuarioModel = EntityModel.of(usuario);

        Mockito.when(usuarioService.guardaUsuario(any(Usuario.class))).thenReturn(usuario);
        Mockito.when(assembler.toModel(usuario)).thenReturn(usuarioModel);

        mockMvc.perform(put("/api/v2/usuario/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "rutUsuario": "12345678-9",
                                  "nombreUsuario": "Juan",
                                  "apellidoUsuario": "Pérez",
                                  "edad": 30,
                                  "correo": "juan.perez@example.com"
                                }
                                """)
                        .accept("application/hal+json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }
    @Test
    void metodoDeleteV2() throws Exception {
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(11);
    usuario.setRutUsuario("12345678-9");
    usuario.setNombreUsuario("Juan");
    usuario.setApellidoUsuario("Pérez");
    usuario.setEdad(30);
    usuario.setCorreo("juan.perez@example.com");

    when(usuarioService.buscarporidUsuario(11)).thenReturn(usuario);
    doNothing().when(usuarioService).eliminarUsuario(11);

    mockMvc.perform(delete("/api/v2/usuario/11")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
}

    private Usuario crearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(11);
        usuario.setRutUsuario("12345678-9");
        usuario.setNombreUsuario("Juan");
        usuario.setApellidoUsuario("Pérez");
        usuario.setEdad(30);
        usuario.setCorreo("juan.perez@example.com");
        return usuario;
    }
}
