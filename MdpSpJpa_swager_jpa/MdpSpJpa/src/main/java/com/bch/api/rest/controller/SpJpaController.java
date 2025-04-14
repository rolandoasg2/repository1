package com.bch.api.rest.controller;



//import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import static com.mdp.app.oauth2.Roles.MDP_CONSULTIVO_PAGOS_MASIVOS;
//import static com.mdp.app.oauth2.Roles.MDP_OPERADOR_VISADO;
//import static com.mdp.app.oauth2.Roles.MDP_OPERADOR_SOPORTE;

import com.bch.api.rest.dto.UsuariosResp;
import com.bch.api.rest.service.UsuariosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//tree /f | findstr /V ".jar"

@RestController
@RequestMapping("/mdp")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@Api(tags = "Gestion de Usuarios", value = "Controlador para operaciones relacionadas con usuarios")
public class SpJpaController {

 
	//http://localhost:8081/MdpSpJpa/v2/api-docs
	//http://localhost:8081/MdpSpJpa/swagger-ui.html
	//http://localhost:8081/MdpSpJpa/swagger-ui/index.html
    //curl -X GET http://localhost:8081/MdpSpJpa/mdp/test
	@GetMapping(value = "/test", headers = "Accept=application/json")
    public String getPrueba() {
        System.out.println("Servicios arriba ...");
        return "Servicios arriba ...";
    }
	


	//----------------------------------------------------
	
    @Autowired
    private UsuariosService usuariosService;

    /**
     * Obtiene la lista de usuarios del sistema.
     *
     * @return ResponseEntity con la respuesta del servicio
     */
    @GetMapping(value = "/usuarios", headers = "Accept=application/json")
    @ApiOperation(
        value = "Obtener todos los usuarios",
        notes = "Retorna una lista con todos los usuarios registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK - Lista de usuarios obtenida exitosamente"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public ResponseEntity<UsuariosResp> getUsuarios() {
        UsuariosResp respuesta;

        try {
            respuesta = usuariosService.getUsuariosData();
            respuesta.setCodigo(200);
            respuesta.setMensaje("OK");
            respuesta.setTimestamp(java.time.LocalDateTime.now().toString());
        } catch (Exception e) {
            respuesta = new UsuariosResp();
            respuesta.setCodigo(500);
            respuesta.setMensaje(e.toString());
            respuesta.setTimestamp(java.time.LocalDateTime.now().toString());
        }

        return ResponseEntity.status(respuesta.getCodigo()).body(respuesta);
    }
}
