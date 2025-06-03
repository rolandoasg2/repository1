package com.bch.api.rest.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtKeyGeneratorShort {
    public static void main(String[] args) {
        byte[] key = new byte[48];
        new SecureRandom().nextBytes(key);
        System.out.println("Clave JWT: " + 
            Base64.getUrlEncoder().withoutPadding().encodeToString(key));
    }
}


/*
 Generador de Secret para el properties, Caracteristicas de seguridad, 
 en PROD debe setar en una variable de ambiente:
Usa SecureRandom (generador criptograficamente seguro)

Longitud de 384 bits (48 bytes) antes de codificación Base64

Codificación URL-safe (sin +/=)

Equivalente en seguridad al comando OpenSSL que mencionaste

*/