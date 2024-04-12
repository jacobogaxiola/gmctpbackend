package gmctpat.webapi.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipos_respuesta")
public class TipoRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false, length = 1)
    private String estructura;

    @Column(columnDefinition = "text")
    private String configuracion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion=new Date();

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    // Getters and setters
}