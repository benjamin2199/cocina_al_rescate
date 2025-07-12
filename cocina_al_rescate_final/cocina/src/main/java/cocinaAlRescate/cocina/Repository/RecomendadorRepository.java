package cocinaAlRescate.cocina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cocinaAlRescate.cocina.Model.Recomendador;

@Repository
public interface  RecomendadorRepository  extends JpaRepository<Recomendador, Long> {
    
}

