package web.beans;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import web.generales.IConstantes;

public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5206995572622885785L;
	private Integer						id;
	private String						titulo;
	private String						tituloIngles;
	private String						contenido;
	private String						contenidoIngles;
	private String						indicativoVigencia;
	private String						poseeContenido;
	private Integer						posicion;

	private List<SubMenu>			tSubmenus;

	private EstructuraTabla		estructuraTabla;

	public Menu() {
		this.estructuraTabla = new EstructuraTabla();

	}

	public void getCamposBD() {

		this.estructuraTabla.setTabla("menus");
		this.estructuraTabla.getLlavePrimaria().put("id", this.id);

		this.estructuraTabla.getPersistencia().put("titulo", this.titulo);
		this.estructuraTabla.getPersistencia().put("titulo_ingles", this.tituloIngles);
		this.estructuraTabla.getPersistencia().put("contenido", this.contenido);
		this.estructuraTabla.getPersistencia().put("contenido_ingles", this.contenidoIngles);

		this.estructuraTabla.getPersistencia().put("indicativo_vigencia", this.indicativoVigencia);
		this.estructuraTabla.getPersistencia().put("posee_contenido", this.poseeContenido);
		this.estructuraTabla.getPersistencia().put("posicion", this.posicion);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Size(max = 100, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Size(max = 100, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getTituloIngles() {
		return tituloIngles;
	}

	public void setTituloIngles(String tituloIngles) {
		this.tituloIngles = tituloIngles;
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

	public String getIndicativoVigencia() {
		return indicativoVigencia;
	}

	public void setIndicativoVigencia(String indicativoVigencia) {
		this.indicativoVigencia = indicativoVigencia;
	}

	public String getPoseeContenido() {
		return poseeContenido;
	}

	public void setPoseeContenido(String poseeContenido) {
		this.poseeContenido = poseeContenido;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public EstructuraTabla getEstructuraTabla() {
		return estructuraTabla;
	}

	public void setEstructuraTabla(EstructuraTabla estructuraTabla) {
		this.estructuraTabla = estructuraTabla;
	}

	public List<SubMenu> gettSubmenus() {
		return tSubmenus;
	}

	public void settSubmenus(List<SubMenu> tSubmenus) {
		this.tSubmenus = tSubmenus;
	}

}
