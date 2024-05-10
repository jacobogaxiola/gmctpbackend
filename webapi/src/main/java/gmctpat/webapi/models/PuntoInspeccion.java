package gmctpat.webapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "puntos_inspeccion")
public class PuntoInspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int secuencia;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "id_tipo_respuesta", nullable = false)
    private Long idTipoRespuesta;

    @Column(name = "fecha_de_creacion", nullable = false)
    @Builder.Default
    private Date fechaDeCreacion = new Date();

    @Column(name = "fecha_de_baja")
    private Date fechaDeBaja;

    // Getters and setters
}
