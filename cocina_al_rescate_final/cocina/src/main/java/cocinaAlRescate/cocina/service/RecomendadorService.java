package cocinaAlRescate.cocina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.Recomendador;
import cocinaAlRescate.cocina.Repository.RecomendadorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecomendadorService {

    @Autowired
    private RecomendadorRepository recomendadorRepository;

    public List<Recomendador> list() {
        return recomendadorRepository.findAll();
    }           

    public Recomendador buscarporidRecomendador(long idRecomendador) {
        return recomendadorRepository.findById(idRecomendador).get();
    }

    public Recomendador guardaRecomendador(Recomendador recomendador) {
        return recomendadorRepository.save(recomendador);
    }

    public void eliminarRecomendador(long idRecomendador) {
        recomendadorRepository.deleteById(idRecomendador);
    }

}
