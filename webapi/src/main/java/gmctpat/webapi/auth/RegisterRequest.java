package gmctpat.webapi.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String correoelectronico;
    String contrasena;
    String nombre;
    String apellidos;
    Long idrol;
}
