package web.beans;

import java.io.Serializable;

import javax.validation.constraints.Size;

import web.generales.IConstantes;

public class Seccion implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5677231738416541603L;
	private Integer						id;
	private String						nombreIdentificacion;
	private String						contenido;
	private String						contenidoIngles;

	private EstructuraTabla		estructuraTabla;

	public Seccion() {
		this.estructuraTabla = new EstructuraTabla();

	}

	public void getCamposBD() {

		this.estructuraTabla.setTabla("secciones_pagina_principal");
		this.estructuraTabla.getLlavePrimaria().put("id", this.id);
		this.estructuraTabla.getPersistencia().put("nombre_identificacion", this.nombreIdentificacion);
		this.estructuraTabla.getPersistencia().put("contenido", this.contenido);
		this.estructuraTabla.getPersistencia().put("contenido_ingles", this.contenidoIngles);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Size(max = 250, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getNombreIdentificacion() {
		return nombreIdentificacion;
	}

	public void setNombreIdentificacion(String nombreIdentificacion) {
		this.nombreIdentificacion = nombreIdentificacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getContenidoIngles() {
		return contenidoIngles;
	}

	public void setContenidoIngles(String contenidoIngles) {
		this.contenidoIngles = contenidoIngles;
	}

	public EstructuraTabla getEstructuraTabla() {
		return estructuraTabla;
	}

	public void setEstructuraTabla(EstructuraTabla estructuraTabla) {
		this.estructuraTabla = estructuraTabla;
	}

}
