package com.proyecto.daw.controller;

import com.proyecto.daw.model.Usuario;
import com.proyecto.daw.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authManager, UsuarioRepository usuarioRepository) {
        this.authManager = authManager;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest, HttpServletRequest request) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            Usuario usuario = usuarioRepository.findByCorreo(email);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("id", usuario.getId());
            response.put("nombre", usuario.getNombre());
            response.put("razonSocial", usuario.getRazonSocial());
            response.put("nombreContacto", usuario.getNombreContacto());
            response.put("correo", usuario.getCorreo()); 
            response.put("dni", usuario.getDni());
            response.put("telefono", usuario.getTelefono());
            response.put("direccion", usuario.getDireccion());
            response.put("localidad", usuario.getLocalidad());
            response.put("codigoPostal", usuario.getCodigoPostal()); 
            response.put("rol", usuario.getRol());
            response.put("roles", authentication.getAuthorities().toString());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email o contraseña incorrectos"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(Map.of("message", "Sesión cerrada correctamente"));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() instanceof String) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "No autenticado"));
        }

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Usuario usuario = usuarioRepository.findByCorreo(userDetails.getUsername());

        
        Map<String, Object> response = new HashMap<>();
        response.put("id", usuario.getId());
        response.put("nombre", usuario.getNombre());
        response.put("razonSocial", usuario.getRazonSocial());
        response.put("nombreContacto", usuario.getNombreContacto());
        response.put("correo", usuario.getCorreo());
        response.put("dni", usuario.getDni());
        response.put("telefono", usuario.getTelefono());
        response.put("direccion", usuario.getDireccion());
        response.put("localidad", usuario.getLocalidad());
        response.put("codigoPostal", usuario.getCodigoPostal());
        response.put("rol", usuario.getRol());
        response.put("roles", auth.getAuthorities().toString());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkSession() {
        return getCurrentUser();
    }
}