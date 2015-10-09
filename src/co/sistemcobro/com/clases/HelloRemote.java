package co.sistemcobro.com.clases;


import java.util.List;

import javax.ejb.Remote;

import co.sistemcobro.rrhh.bean.EmpleadoBean;

@Remote
public interface HelloRemote {
	List<EmpleadoBean> buscarEmpleadospornumeroidentificacion(String numeroidentificacion)  ;
} 