package gmctpat.webapi.repositories;

import gmctpat.webapi.models.PuntoInspeccion;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoInspeccionRepository extends JpaRepository<PuntoInspeccion, Long> {

    Page<PuntoInspeccion> findByFechaDeBajaIsNull(Pageable pageable);
    @SuppressWarnings("null")
    default Page<PuntoInspeccion> findAll(Pageable pageable) {
        return findByFechaDeBajaIsNull(pageable);
    }

    Optional<PuntoInspeccion> findByIdAndFechaDeBajaIsNull(Long id);
    @SuppressWarnings("null")
    default Optional<PuntoInspeccion> findById(Long id) {
        return findByIdAndFechaDeBajaIsNull(id);
    }

}
