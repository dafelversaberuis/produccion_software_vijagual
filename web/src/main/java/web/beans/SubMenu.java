package web.beans;

import java.io.Serializable;

import javax.validation.constraints.Size;

import web.generales.IConstantes;

public class SubMenu implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2706577910924850186L;
	private Integer						id;
	private String						titulo;
	private String						tituloIngles;
	private String						contenido;
	private String						contenidoIngles;
	private String						indicativoVigencia;
	private Integer						posicion;

	private Menu							menu;

	private EstructuraTabla		estructuraTabla;

	public SubMenu() {
		this.estructuraTabla = new EstructuraTabla();
		this.menu = new Menu();
	}

	public void getCamposBD() {

		this.estructuraTabla.setTabla("submenus");
		this.estructuraTabla.getLlavePrimaria().put("id", this.id);

		this.estructuraTabla.getPersistencia().put("titulo", this.titulo);
		this.estructuraTabla.getPersistencia().put("titulo_ingles", this.tituloIngles);
		this.estructuraTabla.getPersistencia().put("contenido", this.contenido);
		this.estructuraTabla.getPersistencia().put("contenido_ingles", this.contenidoIngles);

		this.estructuraTabla.getPersistencia().put("indicativo_vigencia", this.indicativoVigencia);
		this.estructuraTabla.getPersistencia().put("posicion", this.posicion);

		if (this.menu != null && this.menu.getId() != null) {
			this.estructuraTabla.getPersistencia().put("id_menu", this.menu.getId());
		} else {

			this.estructuraTabla.getPersistencia().put("id_menu", null);
		}

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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
