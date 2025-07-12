package cocinaAlRescate.cocina.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cocinaAlRescate.cocina.Model.Preferencia;


@Repository
public interface PreferenciaRepository extends JpaRepository<Preferencia, Long> {
    
}


