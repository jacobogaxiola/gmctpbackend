package gmctpat.webapi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gmctpat.webapi.models.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Page<Rol> findByFechaDeBajaIsNull(Pageable pageable);
    @SuppressWarnings("null")
    default Page<Rol> findAll(Pageable pageable) {
        return findByFechaDeBajaIsNull(pageable);
    }

    Rol findByIdAndFechaDeBajaIsNull(Long id);
    @SuppressWarnings("null")
    default Optional<Rol> findById(Long id) {
        return Optional.ofNullable(findByIdAndFechaDeBajaIsNull(id));
    }

}