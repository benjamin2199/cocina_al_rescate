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
@Table (name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idUsuario;

    @Column(unique=true, length=13, nullable=false)
    private String rutUsuario;

    @Column(nullable=false)
    private String nombreUsuario;

    @Column(nullable=false)
    private String apellidoUsuario;

    @Column(nullable=false)
    private int edad;

    @Column(nullable=false)
    private String correo;

    

}
