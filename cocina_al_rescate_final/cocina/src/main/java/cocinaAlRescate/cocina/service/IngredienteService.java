
package cocinaAlRescate.cocina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.Ingrediente;
import cocinaAlRescate.cocina.Repository.IngredienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> list() {
        return ingredienteRepository.findAll();
    }           

    public Ingrediente buscarporidIngrediente(long idIngrediente) {
        return ingredienteRepository.findById(idIngrediente).get();
    }

    public Ingrediente guardaIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public void eliminarIngrediente(long idIngrediente) {
        ingredienteRepository.deleteById(idIngrediente);
    }

}
