package gmctpat.webapi.controllers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmctpat.webapi.models.TipoRespuesta;
import gmctpat.webapi.repositories.TipoRespuestaRepository;

@RestController
@RequestMapping("/api/tiposrespuesta")
public class TipoRespuestaController {

    @Autowired
    private TipoRespuestaRepository tipoRespuestaRepository;

    @GetMapping
    public ResponseEntity<ArrayList<TipoRespuesta>> getAllTiposRespuesta() {
        ArrayList<TipoRespuesta> tiposRespuesta = (ArrayList<TipoRespuesta>) tipoRespuestaRepository.findAll();
        return new ResponseEntity<>(tiposRespuesta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRespuesta> getTipoRespuestaById(@PathVariable Long id) {
        TipoRespuesta tipoRespuesta = tipoRespuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de respuesta not found with id " + id));
        return new ResponseEntity<>(tipoRespuesta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipoRespuesta> createTipoRespuesta(@RequestBody TipoRespuesta tipoRespuesta) {
        TipoRespuesta newTipoRespuesta = tipoRespuestaRepository.save(tipoRespuesta);
        return new ResponseEntity<>(newTipoRespuesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoRespuesta> updateTipoRespuesta(@PathVariable Long id, @RequestBody TipoRespuesta tipoRespuestaDetails) {
        TipoRespuesta tipoRespuesta = tipoRespuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de respuesta not found with id " + id));

        tipoRespuesta.setNombre(tipoRespuestaDetails.getNombre());
        tipoRespuesta.setDescripcion(tipoRespuestaDetails.getDescripcion());
        tipoRespuesta.setEstructura(tipoRespuestaDetails.getEstructura());
        tipoRespuesta.setConfiguracion(tipoRespuestaDetails.getConfiguracion());
        tipoRespuesta.setFechaBaja(tipoRespuestaDetails.getFechaBaja());

        TipoRespuesta updatedTipoRespuesta = tipoRespuestaRepository.save(tipoRespuesta);
        return new ResponseEntity<>(updatedTipoRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoRespuesta(@PathVariable Long id) {
        TipoRespuesta tipoRespuesta = tipoRespuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de respuesta not found with id " + id));

        tipoRespuesta.setFechaBaja(new Date());

        tipoRespuestaRepository.save(tipoRespuesta);
        return ResponseEntity.ok().build();
    }
}