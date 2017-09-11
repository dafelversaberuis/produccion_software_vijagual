package web.beans;

import java.io.Serializable;

public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1794066230607804275L;
	private Integer						id;
	private String						nombre;

	public Area() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
