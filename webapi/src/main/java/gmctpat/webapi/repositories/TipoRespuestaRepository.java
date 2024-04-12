package gmctpat.webapi.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gmctpat.webapi.models.TipoRespuesta;

@Repository
public interface TipoRespuestaRepository extends JpaRepository<TipoRespuesta, Long> {
    ArrayList<TipoRespuesta> findByFechaBajaIsNull();
    @SuppressWarnings("null")
    default ArrayList<TipoRespuesta> findAll() {
        return findByFechaBajaIsNull();
    }

    TipoRespuesta findByIdAndFechaBajaIsNull(Long id);
    @SuppressWarnings("null")
    default Optional<TipoRespuesta> findById(Long id) {
        return Optional.ofNullable(findByIdAndFechaBajaIsNull(id));
    }
}