package gmctpat.webapi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gmctpat.webapi.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByFechaDeBajaIsNull(Pageable pageable);
    @SuppressWarnings("null")
    default Page<Usuario> findAll(Pageable pageable) {
        return findByFechaDeBajaIsNull(pageable);
    }

    Usuario findByIdAndFechaDeBajaIsNull(Long id);
    @SuppressWarnings("null")
    default Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(findByIdAndFechaDeBajaIsNull(id));
    }

    Optional<Usuario> findByNombre(String nombre);

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

}