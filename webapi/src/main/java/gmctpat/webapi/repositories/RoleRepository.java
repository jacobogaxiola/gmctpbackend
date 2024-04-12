package gmctpat.webapi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gmctpat.webapi.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findByFechaDeBajaIsNull(Pageable pageable);
    @SuppressWarnings("null")
    default Page<Role> findAll(Pageable pageable) {
        return findByFechaDeBajaIsNull(pageable);
    }

    Role findByIdAndFechaDeBajaIsNull(Long id);
    @SuppressWarnings("null")
    default Optional<Role> findById(Long id) {
        return Optional.ofNullable(findByIdAndFechaDeBajaIsNull(id));
    }

}