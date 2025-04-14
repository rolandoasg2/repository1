package com.bch.api.rest.service;

import com.bch.api.rest.repository.UsuariosRepository;
import com.bch.api.rest.dto.UsuariosResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public UsuariosResp getUsuariosData() {
        return usuariosRepository.getUsuariosData();
    }
}
