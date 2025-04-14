package com.bch.api.rest.dto;

import java.util.List;

public class UsuariosResp {


    List<Usuarios> data;
    int codigo; 
    String mensaje;
    String timestamp;
	String  vcEstado;
	private String vcEstadoCreacion;

    

	public List<Usuarios> getData() {
		return data;
	}
	public void setData(List<Usuarios> data) {
		this.data = data;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
    
	public String getVcEstado() {
		return vcEstado;
	}
	public void setVcEstado(String vcEstado) {
		this.vcEstado = vcEstado;
	}
	
	public String getVcEstadoCreacion() {
		return vcEstadoCreacion;
	}
	public void setVcEstadoCreacion(String vcEstadoCreacion) {
		this.vcEstadoCreacion = vcEstadoCreacion;
	}
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
	
	
}
