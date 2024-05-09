package gmctpat.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gmctpat.webapi.models.Rol;
import gmctpat.webapi.repositories.RolRepository;

import java.util.Date;

@RestController
@RequestMapping("/api/roles") // .../api/roles?page=0&size=10&sortBy=nombre&sortDir=asc
public class RoleController {

    @Autowired
    private RolRepository roleRepository;

    @GetMapping
    public ResponseEntity<Page<Rol>> getAllRoles(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "nombre") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Rol> roles = roleRepository.findAll(pageable);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRoleById(@PathVariable Long id) {
        Rol role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Rol> createRole(@RequestBody Rol role) {
        Rol newRole = roleRepository.save(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRole(@PathVariable Long id, @RequestBody Rol roleDetails) {
        Rol role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));

        role.setNombre(roleDetails.getNombre());
        role.setDescripcion(roleDetails.getDescripcion());
        role.setFechaDeBaja(roleDetails.getFechaDeBaja());
        role.setTipo(roleDetails.getTipo());

        Rol updatedRole = roleRepository.save(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        Rol role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));

        role.setFechaDeBaja(new Date());
        roleRepository.save(role);
        return ResponseEntity.ok().build();
    }
}