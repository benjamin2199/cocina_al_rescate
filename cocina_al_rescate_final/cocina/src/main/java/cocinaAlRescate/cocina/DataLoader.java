package cocinaAlRescate.cocina;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import cocinaAlRescate.cocina.Model.Ingrediente;
import cocinaAlRescate.cocina.Model.ListaCompra;
import cocinaAlRescate.cocina.Model.MenuSemanal;
import cocinaAlRescate.cocina.Model.Notificacion;
import cocinaAlRescate.cocina.Model.Preferencia;
import cocinaAlRescate.cocina.Model.Receta;
import cocinaAlRescate.cocina.Model.Recomendador;
import cocinaAlRescate.cocina.Model.Usuario;
import cocinaAlRescate.cocina.Repository.IngredienteRepository;
import cocinaAlRescate.cocina.Repository.ListaCompraRepository;
import cocinaAlRescate.cocina.Repository.MenuSemanalRepository;
import cocinaAlRescate.cocina.Repository.NotificacionRepository;
import cocinaAlRescate.cocina.Repository.PreferenciaRepository;
import cocinaAlRescate.cocina.Repository.RecetaRepository;
import cocinaAlRescate.cocina.Repository.RecomendadorRepository;
import cocinaAlRescate.cocina.Repository.UsuarioRepository;
import net.datafaker.Faker;
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PreferenciaRepository preferenciaRepository;
    @Autowired
    private RecetaRepository recetaRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;
    @Autowired
    private RecomendadorRepository recomendadorRepository;
    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private MenuSemanalRepository menuSemanalRepository;
    @Autowired
    private ListaCompraRepository listaCompraRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Crear usuarios
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setRutUsuario(faker.idNumber().valid());
            usuario.setNombreUsuario(faker.name().firstName());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setEdad(faker.number().numberBetween(18, 60));
            usuario.setCorreo(faker.internet().emailAddress());
            usuarios.add(usuarioRepository.save(usuario));
        }

        // Crear preferencias
        List<Preferencia> preferencias = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Preferencia preferencia = new Preferencia();
            preferencia.setDieta(faker.food().spice());
            preferencia.setPorciones(faker.number().numberBetween(1, 6));
            preferencias.add(preferenciaRepository.save(preferencia));
        }

        // Crear listas de compra
        List<ListaCompra> listasCompra = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ListaCompra lista = new ListaCompra();
            lista.setNombreLista("Lista " + faker.number().digit());
            listasCompra.add(listaCompraRepository.save(lista));
        }

        // Crear ingredientes
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNombreIngrediente(faker.food().ingredient());
            ingrediente.setCantidad(faker.number().numberBetween(1, 5) + " unidades");
            ingrediente.setFechaVence(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toString());
            ingrediente.setListaCompra(listasCompra.get(random.nextInt(listasCompra.size())));
            ingredientes.add(ingredienteRepository.save(ingrediente));
        }

        // Crear recetas
        List<Receta> recetas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Receta receta = new Receta();
            receta.setNombreReceta(faker.food().dish());
            receta.setPasos(faker.number().numberBetween(3, 10));
            receta.setDificultad(faker.options().option("Fácil", "Media", "Difícil"));
            receta.setTiempo(faker.number().numberBetween(10, 90));
            receta.setTipo(faker.options().option("Desayuno", "Almuerzo", "Cena"));
            receta.setIngredientes(ingredientes.get(random.nextInt(ingredientes.size())));
            receta.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            recetas.add(recetaRepository.save(receta));
        }

       // Crear recomendadores
        for (int i = 0; i < 5; i++) {
            Recomendador recomendador = new Recomendador();
            recomendador.setPreferenciaUsuario(preferencias.get(random.nextInt(preferencias.size())));
    // Selecciona recetas aleatorias sin repetir para cada recomendador
            int cantidadRecetas = random.nextInt(recetas.size() - 1) + 1; // al menos 1 receta
            List<Receta> copiaRecetas = new ArrayList<>(recetas);
            List<Receta> recetasAleatorias = new ArrayList<>();
            for (int j = 0; j < cantidadRecetas; j++) {
                int idx = random.nextInt(copiaRecetas.size());
                recetasAleatorias.add(copiaRecetas.remove(idx));
            }
            recomendador.setReceta(recetasAleatorias);
            recomendadorRepository.save(recomendador);
        }

        // Crear notificaciones
        for (Usuario usuario : usuarios) {
            Notificacion notificacion = new Notificacion();
            notificacion.setTipo("Info");
            notificacion.setMensaje(faker.lorem().sentence());
            notificacion.setFecha(new Date(System.currentTimeMillis()).toString());
            notificacion.setUsuario(usuario);
            notificacionRepository.save(notificacion);
        }

        // Crear menús semanales
        for (int i = 0; i < 5; i++) {
            MenuSemanal menu = new MenuSemanal();
            menu.setNombreMenuS("Menú Semana " + (i + 1));
            menuSemanalRepository.save(menu);
        }
    }
}
