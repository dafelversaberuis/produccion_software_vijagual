package web.beans;

import java.io.Serializable;

public class Contactenos implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8529888779288621461L;
	private String						fecha;
	private String						fechaArchivo;

	private String						nombres;

	private String						telefonos;

	private String						correo;
	private String						descripcion;
	private String						asunto;

	private Area							area;

	public Contactenos() {
		this.area = new Area();
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaArchivo() {
		return fechaArchivo;
	}

	public void setFechaArchivo(String fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
