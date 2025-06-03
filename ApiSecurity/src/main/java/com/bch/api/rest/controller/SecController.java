package com.bch.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SecController {

    /**
     * Endpoint publico accesible sin autenticación
     */
    @GetMapping("/public/test")
    public ResponseEntity<Map<String, String>> publicTest() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Acceso publico permitido");
        response.put("status", "OK");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint solo para administradores
     */
    @GetMapping("/admin/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> adminEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenido Administrador");
        response.put("status", "OK");
        response.put("user", auth.getName());
        response.put("authorities", auth.getAuthorities());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para usuarios con rol USER o ADMIN
     */
    @GetMapping("/user/hello")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> userEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenido Usuario");
        response.put("status", "OK");
        response.put("user", auth.getName());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para analistas
     */
    @GetMapping("/analyst/data")
    @PreAuthorize("hasRole('ROLE_ANALYST')")
    public ResponseEntity<Map<String, Object>> analystEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Datos confidenciales para analistas");
        response.put("status", "OK");
        response.put("user", auth.getName());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para multiples roles
     */
    @GetMapping("/manager/dashboard")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> managerDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Panel de gestión");
        response.put("status", "OK");
        response.put("user", auth.getName());
        response.put("role", auth.getAuthorities().iterator().next().getAuthority());
        return ResponseEntity.ok(response);
    }
}


/*
Proceso de validación:

Valida primero las credenciales del cliente (clientapp:123456)
Luego usa el AuthenticationManager para validar admin:Clave123
El AuthenticationManager delega en CustomUserDetailsService
CustomUserDetailsService verifica en la base de datos
Compara contraseñas usando el PasswordEncoder (BCrypt)
Si la validación es exitosa:
Genera el token JWT con los roles del usuario
Devuelve el token al cliente


curl -X GET  http://localhost:8081/ApiSecurity/api/security/public/test  -H "Content-Type: application/json" -H "Accept: application/json"
curl -X POST "http://localhost:8081/ApiSecurity/oauth/token" -H "Authorization: Basic Y2xpZW50YXBwOjEyMzQ1Ng==" -H "Content-Type: application/x-www-form-urlencoded" -d "username=admin&password=Clave123&grant_type=password"
curl -X GET "http://localhost:8081/ApiSecurity/api/security/admin/hello" -H "Authorization: Bearer TU_TOKEN_JWT_AQUI" -H "Content-Type: application/json" -H "Accept: application/json"

*/