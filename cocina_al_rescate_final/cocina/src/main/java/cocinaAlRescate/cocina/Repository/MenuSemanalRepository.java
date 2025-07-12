package cocinaAlRescate.cocina.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cocinaAlRescate.cocina.Model.MenuSemanal;


@Repository
public interface MenuSemanalRepository extends JpaRepository<MenuSemanal, Long> {
    
}
