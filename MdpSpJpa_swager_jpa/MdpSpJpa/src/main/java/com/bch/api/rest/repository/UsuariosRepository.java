package com.bch.api.rest.repository;

import com.bch.api.rest.dto.Usuarios;
import com.bch.api.rest.dto.UsuariosResp;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class UsuariosRepository {

    private static final Logger logger = LogManager.getLogger(UsuariosRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public UsuariosResp getUsuariosData() {
        UsuariosResp respuesta = new UsuariosResp();
        List<Usuarios> usuarios = new ArrayList<>();

        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerUsuarios");

            query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);

            query.execute();

            List<Object[]> results = query.getResultList();
            if (results != null) {
                for (Object[] row : results) {
                    Usuarios usuario = new Usuarios();
                    usuario.setNombre((String) row[0]);
                    usuario.setApellido((String) row[1]);
                    usuarios.add(usuario);
                }
            }

            respuesta.setVcEstado((String) query.getOutputParameterValue(2));
            respuesta.setVcEstadoCreacion((String) query.getOutputParameterValue(3));

        } catch (Exception e) {
            logger.error("Error al ejecutar el procedimiento almacenado: {}", e.getMessage(), e);
            entityManager.clear();
            respuesta.setVcEstado("ERROR");
            respuesta.setVcEstadoCreacion("Error en la ejecución del proceso. Consulte los logs.");
        }

        respuesta.setData(usuarios);
        return respuesta;
    }

}


/*
package com.bch.api.rest.repository;

import com.bch.api.rest.dto.Usuarios;
import com.bch.api.rest.dto.UsuariosResp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuariosRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UsuariosResp getUsuariosData() {
        UsuariosResp respuesta = new UsuariosResp();
        List<Usuarios> usuarios = new ArrayList<>();
        String vcEstado = "";
        String vcEstadoCreacion = "";

        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerUsuarios");

            query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);

            query.execute();

            List<Object[]> results = query.getResultList();

            for (Object[] row : results) {
                Usuarios usuario = new Usuarios();
                usuario.setNombre((String) row[0]);
                usuario.setApellido((String) row[1]);
                usuarios.add(usuario);
            }

            vcEstado = (String) query.getOutputParameterValue(2);
            vcEstadoCreacion = (String) query.getOutputParameterValue(3);

        } catch (Exception e) {
            vcEstado = "ERROR";
            vcEstadoCreacion = "Error en la ejecucion del proceso: " + e.getMessage();
        }

        respuesta.setData(usuarios);
        respuesta.setVcEstado(vcEstado);
        respuesta.setVcEstadoCreacion(vcEstadoCreacion);
        return respuesta;
    }
}*/