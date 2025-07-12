package cocinaAlRescate.cocina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ingrediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIngrediente;

    @Column(nullable = false)
    private String nombreIngrediente;

    @Column(nullable = false)
    private String cantidad;

    @Column(nullable = false)
    private String fechaVence;

    @ManyToOne
    @JoinColumn(name = "listaCompra_id")
    private ListaCompra listaCompra;

}
