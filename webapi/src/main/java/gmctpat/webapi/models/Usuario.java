package gmctpat.webapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false, name = "correo_electronico")
    private String correoElectronico;
    @Column(nullable = false, name = "id_rol")
    private Long idRol;
    @Column(nullable = false, name = "fecha_de_registro")
    @Builder.Default
    private Date fechaDeRegistro=new Date();
    @Column(name = "fecha_de_cambio_de_contrasena")
    private Date fechaDeCambioDeContrasena;
    @Column(name = "fecha_de_baja")
    private Date fechaDeBaja;
    @Column(name =  "hash_de_contrasena")
    private String hashDeContrasena;
    @Column(name = "token_de_seguridad")
    private String tokenDeSeguridad;
    @Column(name = "fecha_de_vigencia_de_token")
    private Date fechaDeVigenciaDeToken;
    //Rol role;
    // Getters and setters
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ALL"));
        //return null;
    }
    @Override
    public String getPassword() {
        return hashDeContrasena;
    }
    @Override
    public String getUsername() {
        return correoElectronico;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}