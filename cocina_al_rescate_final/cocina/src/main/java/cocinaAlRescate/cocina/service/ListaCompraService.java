package cocinaAlRescate.cocina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.ListaCompra;
import cocinaAlRescate.cocina.Repository.ListaCompraRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ListaCompraService  {

    @Autowired
    private ListaCompraRepository listaCompraRepository;

    public List<ListaCompra> list() {
        return listaCompraRepository.findAll();
    }           

    public ListaCompra buscarporidListaCompra(long idListaCompra) {
        return listaCompraRepository.findById(idListaCompra).get();
    }

    public ListaCompra guardaListaCompra(ListaCompra listaCompra) {
        return listaCompraRepository.save(listaCompra);
    }

    public void eliminarListaCompra(long idListaCompra) {
        listaCompraRepository.deleteById(idListaCompra);
    }

}
