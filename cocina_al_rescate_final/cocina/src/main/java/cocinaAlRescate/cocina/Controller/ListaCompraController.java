package cocinaAlRescate.cocina.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cocinaAlRescate.cocina.Model.ListaCompra;
import cocinaAlRescate.cocina.service.ListaCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/listacompra")
@Tag(name = "Lista de Compras", description = "Operaciones relacionadas a la lista de compras") 
public class ListaCompraController {
@Autowired
    private ListaCompraService listaCompraService;

    @GetMapping
    @Operation(summary = "Obtener todas las listas de compras", description = "Obtiene una lista de todas las listas de compras") 
    public ResponseEntity<List<ListaCompra>> listar() {
        List<ListaCompra> listaCompras = listaCompraService.list();
        if (listaCompras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaCompras);
}
    @PostMapping("/guardar")
    @Operation(summary = "Guardar una lista de compras", description = "Guarda una nueva lista de compras en el sistema")
    public ResponseEntity<ListaCompra> guardarlistaCompra(@RequestBody ListaCompra listaCompra) {
        ListaCompra nuevolistaCompra = listaCompraService.guardaListaCompra(listaCompra);
        return new ResponseEntity<>(nuevolistaCompra, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idlistaCompra}")
    @Operation(summary = "Buscar lista de compras por ID", description = "Busca una lista de compras específica por su ID")
    public ResponseEntity<ListaCompra> buscarlistaCompra(@RequestParam("idlistaCompra") int idlistaCompra) {
        ListaCompra listaCompra = listaCompraService.buscarporidListaCompra(idlistaCompra);
        if (listaCompra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaCompra);
    }
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar una lista de compras", description = "Actualiza una lista de compras existente en el sistema")
    public ResponseEntity<ListaCompra> actualizarlistaCompra(@RequestBody ListaCompra listaCompra) {
        ListaCompra listaCompraActualizado = listaCompraService.guardaListaCompra(listaCompra);
        if (listaCompraActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaCompraActualizado);
    }
    @DeleteMapping("/eliminar/{idlistaCompra}")
    @Operation(summary = "Eliminar una lista de compras", description = "Elimina una lista de compras existente en el sistema")
    public ResponseEntity<String> eliminarlistaCompra(@PathVariable("idlistaCompra") int idlistaCompra){
        listaCompraService.eliminarListaCompra(idlistaCompra);
        return ResponseEntity.ok("listaCompra eliminado");
    }
}
