package gmctpat.webapi.controllers;

import gmctpat.webapi.models.PuntoInspeccion;
import gmctpat.webapi.repositories.PuntoInspeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/puntosinspeccion")
public class PuntoInspeccionController {

    @Autowired
    private PuntoInspeccionRepository puntoInspeccionRepository;

    @GetMapping
    public ResponseEntity<Page<PuntoInspeccion>> getAllPuntosInspeccion(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<PuntoInspeccion> puntosInspeccion = puntoInspeccionRepository.findByFechaDeBajaIsNull(pageable);
        return new ResponseEntity<>(puntosInspeccion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoInspeccion> getPuntoInspeccionById(@PathVariable Long id) {
        PuntoInspeccion puntoInspeccion = puntoInspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoInspeccion not found with id " + id));
        return new ResponseEntity<>(puntoInspeccion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PuntoInspeccion> createPuntoInspeccion(@RequestBody PuntoInspeccion puntoInspeccion) {
        PuntoInspeccion newPuntoInspeccion = puntoInspeccionRepository.save(puntoInspeccion);
        return new ResponseEntity<>(newPuntoInspeccion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoInspeccion> updatePuntoInspeccion(@PathVariable Long id, @RequestBody PuntoInspeccion puntoInspeccionDetails) {
        PuntoInspeccion puntoInspeccion = puntoInspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoInspeccion not found with id " + id));

        puntoInspeccion.setSecuencia(puntoInspeccionDetails.getSecuencia());
        puntoInspeccion.setNombre(puntoInspeccionDetails.getNombre());
        puntoInspeccion.setDescripcion(puntoInspeccionDetails.getDescripcion());
        puntoInspeccion.setIdTipoRespuesta(puntoInspeccionDetails.getIdTipoRespuesta());
        puntoInspeccion.setFechaDeBaja(puntoInspeccionDetails.getFechaDeBaja());

        PuntoInspeccion updatedPuntoInspeccion = puntoInspeccionRepository.save(puntoInspeccion);
        return new ResponseEntity<>(updatedPuntoInspeccion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoInspeccion(@PathVariable Long id) {
        PuntoInspeccion puntoInspeccion = puntoInspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PuntoInspeccion not found with id " + id));

        puntoInspeccion.setFechaDeBaja(new Date());
        puntoInspeccionRepository.save(puntoInspeccion);
        return ResponseEntity.ok().build();
    }
}
