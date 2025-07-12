package cocinaAlRescate.cocina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cocinaAlRescate.cocina.Model.Notificacion;


@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    
}



