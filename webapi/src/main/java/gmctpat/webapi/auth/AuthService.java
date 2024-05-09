package gmctpat.webapi.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gmctpat.webapi.jwt.JwtService;
import gmctpat.webapi.models.Usuario;
import gmctpat.webapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreoelectronico(), request.getContrasena()));
        UserDetails user=usuarioRepository.findByCorreoElectronico(request.getCorreoelectronico()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse
            .builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
            .correoElectronico(request.getCorreoelectronico())
            .hashDeContrasena(passwordEncoder.encode(request.getContrasena()))
            .nombre(request.getNombre())
            .apellidos(request.getApellidos())
            .idRol(request.getIdrol())
            .build();
        usuarioRepository.save(usuario);
        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }

}
