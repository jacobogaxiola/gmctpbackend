package gmctpat.webapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "fecha_de_creacion")
    private Date fechaDeCreacion=new Date();

    @Column(name = "fecha_de_baja")
    private Date fechaDeBaja;

    @Column(nullable = false, length = 1)
    private String tipo;

    // Getters and setters
}