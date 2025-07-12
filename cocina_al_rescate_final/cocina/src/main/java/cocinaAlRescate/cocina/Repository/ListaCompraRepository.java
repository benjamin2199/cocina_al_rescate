package cocinaAlRescate.cocina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cocinaAlRescate.cocina.Model.ListaCompra;


@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long> {
    
}
