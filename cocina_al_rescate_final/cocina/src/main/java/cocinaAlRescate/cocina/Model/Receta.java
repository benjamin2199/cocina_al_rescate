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
@Table(name = "Receta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceta;

    @Column(nullable = false)
    private String nombreReceta;

    @Column(nullable = false)
    private int pasos;

    @Column(nullable = false)
    private String dificultad;

    @Column(nullable = false)
    private int tiempo;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "ingredientes_id")
    private Ingrediente ingredientes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}