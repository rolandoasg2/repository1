package com.bch.api.rest.utils;

import java.util.Base64;



// ejecutar con boton derecho y ejecutar como java
public class AuthUtils {

    public static String generateBasicAuthToken(String clientId, String clientSecret) {
        String credentials = clientId + ":" + clientSecret;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    // Método main de ejemplo para probar
    public static void main(String[] args) {
        String clientId = "clientapp";
        String clientSecret = "123456";
        
        String basicAuthToken = generateBasicAuthToken(clientId, clientSecret);
        System.out.println("Token Basic Auth: " + basicAuthToken);
        // Debería imprimir: Y2xpZW50YXBwOjEyMzQ1Ng==
    }
}