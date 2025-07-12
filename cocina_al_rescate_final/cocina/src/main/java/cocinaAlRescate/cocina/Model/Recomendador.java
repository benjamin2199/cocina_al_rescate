package cocinaAlRescate.cocina.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Recomendador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recomendador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRecomendador;

    @ManyToOne
    @JoinColumn(name = "preferenciaUsuario_id")
    private Preferencia preferenciaUsuario;

    @ManyToMany
    @JoinTable(name = "recomendador_receta", joinColumns = @JoinColumn(name = "recomendador_id_recomendador"),
    inverseJoinColumns = @JoinColumn(name = "receta_id_receta")
    )private List<Receta> receta;
}
