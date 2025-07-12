package cocinaAlRescate.cocina.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocinaAlRescate.cocina.Model.Notificacion;
import cocinaAlRescate.cocina.Repository.NotificacionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotIficacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> list() {
        return notificacionRepository.findAll();
    }           

    public Notificacion buscarporidNotificacion(long idNotificacion) {
        return notificacionRepository.findById(idNotificacion).get();
    }

    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void eliminarNotificacion(long idNotificacion) {
        notificacionRepository.deleteById(idNotificacion);
    }



}
