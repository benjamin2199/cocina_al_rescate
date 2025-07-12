package cocinaAlRescate.cocina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.Receta;
import cocinaAlRescate.cocina.Repository.RecetaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> list() {
        return recetaRepository.findAll();
    }           

    public Receta buscarporidReceta(long idReceta) {
        return recetaRepository.findById(idReceta).get();
    }

    public Receta guardaReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(long idReceta) {
        recetaRepository.deleteById(idReceta);
    }

}
