package cocinaAlRescate.cocina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.Preferencia;
import cocinaAlRescate.cocina.Repository.PreferenciaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PreferenciaService  {

    @Autowired
    private PreferenciaRepository preferenciaRepository;

    public List<Preferencia> list() {
        return preferenciaRepository.findAll();
    }           

    public Preferencia buscarporidPreferencia(long idPreferencia) {
        return preferenciaRepository.findById(idPreferencia).get();
    }

    public Preferencia guardaPreferencia(Preferencia preferencia) {
        return preferenciaRepository.save(preferencia);
    }

    public void eliminarPreferencia(long idPreferencia) {
        preferenciaRepository.deleteById(idPreferencia);
    }

}
