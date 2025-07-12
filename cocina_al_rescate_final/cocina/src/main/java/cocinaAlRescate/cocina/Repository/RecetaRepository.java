package cocinaAlRescate.cocina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cocinaAlRescate.cocina.Model.Receta;

@Repository
public interface  RecetaRepository extends JpaRepository<Receta, Long> {
    
}

