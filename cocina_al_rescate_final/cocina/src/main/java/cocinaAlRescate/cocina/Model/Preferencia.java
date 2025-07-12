package cocinaAlRescate.cocina.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Preferencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preferencia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idPreferencia;

    @Column(nullable=false)
    private String  dieta;

    @Column(nullable=false)
    private int porciones;


}
