package cocinaAlRescate.cocina.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import cocinaAlRescate.cocina.Model.Usuario;
import cocinaAlRescate.cocina.Repository.UsuarioRepository;
import cocinaAlRescate.cocina.service.UsuarioService;

@SpringBootTest
public class UsuarioServiceTests {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void  testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1, "12345678-9", "Juan", "Pérez", 20, "juan.perez@example.com")));


        List<Usuario> usuarios = usuarioService.list();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("12345678-9", usuarios.get(0).getRutUsuario());
        assertEquals("Juan", usuarios.get(0).getNombreUsuario());
        assertEquals("Pérez", usuarios.get(0).getApellidoUsuario());
        assertEquals("juan.perez@example.com", usuarios.get(0).getCorreo());
    }

    @Test
    public void testBuscarporidUsuario() {
        int idUsuario = 1;
        Usuario usuario = new Usuario(idUsuario, "12345678-9", "Juan", "Pérez", 20, "juan.perez@example.com");
        when(usuarioRepository.findById((long) idUsuario)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarporidUsuario(idUsuario);

        assertNotNull(resultado);
        assertEquals(idUsuario, resultado.getIdUsuario());
    }

    @Test
    public void testGuardaUsuario() {
        Usuario usuario = new Usuario(1, "12345678-9", "Juan", "Pérez", 0, "juan.perez@example.com");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardaUsuario(usuario);
        assertNotNull(resultado);
        assertEquals(usuario.getIdUsuario(), resultado.getIdUsuario());
    }

    @Test
    public void testEliminarUsuario() {
        long idUsuario = 1;
        doNothing().when(usuarioRepository).deleteById(idUsuario);
        
        usuarioService.eliminarUsuario(idUsuario);
        verify(usuarioRepository, times(1)).deleteById(idUsuario);
    }

}