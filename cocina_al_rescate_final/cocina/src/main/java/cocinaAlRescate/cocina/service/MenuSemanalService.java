
package cocinaAlRescate.cocina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.MenuSemanal;
import cocinaAlRescate.cocina.Repository.MenuSemanalRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuSemanalService {

    @Autowired
    private MenuSemanalRepository menuSemanalRepository;

    public List<MenuSemanal> list() {
        return menuSemanalRepository.findAll();
    }           

    public MenuSemanal buscarporidMenuSemanal(long idMenuSemanal) {
        return menuSemanalRepository.findById(idMenuSemanal).get();
    }

    public MenuSemanal guardaMenuSemanal(MenuSemanal menuSemanal) {
        return menuSemanalRepository.save(menuSemanal);
    }

    public void eliminarMenuSemanal(long idMenuSemanal) {
        menuSemanalRepository.deleteById(idMenuSemanal);
    }

}