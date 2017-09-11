package web.modulos.contenido;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;

import web.Conexion;
import web.beans.Administrador;
import web.beans.Area;
import web.beans.Baner;
import web.beans.Buscador;
import web.beans.Contactenos;
import web.beans.HojaVida;
import web.beans.Menu;
import web.beans.Planillaje;
import web.beans.Pqr;
import web.beans.Seccion;
import web.beans.SubMenu;
import web.generales.ConsultarFuncionesAPI;
import web.generales.IConstantes;
import web.generales.IEmail;
import web.modulos.IConsultasDAO;

@ManagedBean
@ViewScoped
public class AdministrarContenido extends ConsultarFuncionesAPI implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5275794290651443359L;

	private Planillaje				planillaje;
	private Pqr								pqr;
	private HojaVida					hojaVida;
	private Contactenos				cotactenos;

	private String						idSeccion;
	private Menu							menu;
	private Menu							menuTransaccion;

	private Baner							fotoBaner;
	private Baner							fotoBanerTransaccion;

	private Menu							menuConsultado;
	private SubMenu						subMenuConsultado;
	private Seccion						seccionTransaccion;

	private SubMenu						submenu;
	private SubMenu						submenuTransaccion;

	private List<Menu>				menus;
	private List<Menu>				menusActivos;

	private List<SubMenu>			submenus;
	private List<Seccion>			secciones;
	private List<Baner>				fotosBaners;

	private List<Planillaje>	planillajes;

	private String						idioma;

	private Locale						locale;

	@PostConstruct
	public void init() {

		// if (this.idioma != null &&
		// this.idioma.equals(IConstantes.IDIOMA_ESPANOL)) {
		//
		// } else if (this.idioma == null) {
		//
		// } else {
		//
		// }
		// obtiene el local actual
		// locale =
		// FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public List<Buscador> getResultados(String aTexto, String aIdioma) {
		List<Buscador> resultados = new ArrayList<Buscador>();
		try {
			if (aTexto != null && !aTexto.trim().equals("")) {
				resultados = IConsultasDAO.getBuscado(aTexto, aIdioma);
			}
		} catch (Exception e) {

		}

		return resultados;
	}

	/**
	 * Obtiene el idioma actual
	 * 
	 * @return idioma
	 */
	public String getIdioma() {
		if (this.idioma == null) {
			// this.idioma = "es";
		}

		if (this.idioma == null || this.idioma.equals("es")) {
			locale = new Locale("es");
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} else {
			locale = new Locale("en");
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

		}
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getUrlActual() {
		String urlNavegador = "";
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			urlNavegador = req.getRequestURI();

			if (urlNavegador.endsWith("planillaje.jsf")) {
				urlNavegador = "planillaje.jsf";

			} else if (urlNavegador.endsWith("contactenos.jsf")) {

				urlNavegador = "contactenos.jsf";

			} else if (urlNavegador.endsWith("pqrs.jsf")) {

				urlNavegador = "pqrs.jsf";

			} else if (urlNavegador.endsWith("hojaVida.jsf")) {

				urlNavegador = "hojaVida.jsf";

			}
		} catch (Exception e) {

		}

		return urlNavegador;

	}

	/**
	 * Obtiene el idioma actual
	 * 
	 * @return locale
	 */
	public Locale getLocale() {
		// if (this.idioma == null || this.idioma.equals("es")) {
		// locale = new Locale("es");
		// FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		// } else {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idioma = params.get("i");
		if (idioma == null) {
			locale = new Locale("es");

		} else {
			locale = new Locale(idioma);
		}
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

		// }
		return locale;
	}

	/**
	 * Establece el idiomaactual
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	// privados

	/**
	 * Validar la foto del banner
	 * 
	 * @return ok
	 */
	private boolean isValidaFotoBaner() {
		boolean ok = true;

		if (this.isVacio(this.fotoBaner.getLeyenda())) {
			ok = false;
			this.mostrarMensajeGlobal("campoEstaVacio", "advertencia");
		} else {
			this.fotoBaner.setLeyenda(this.fotoBaner.getLeyenda().trim());
		}

		if (this.fotoBaner.getArchivo() == null) {
			ok = false;
			this.mostrarMensajeGlobal("fotoRequerida", "advertencia");
		}

		return ok;
	}

	/**
	 * Este método cambia el idioma dl software de forma manual
	 */
	public void cambiarIdioma() {

		if (this.idioma != null && this.idioma.equals(IConstantes.IDIOMA_ESPANOL)) {

			locale = new Locale("es");
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

			// FacesContext.getCurrentInstance().getViewRoot().setLocale(new
			// Locale("es"));

		} else {

			locale = new Locale("en");
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

			// FacesContext.getCurrentInstance().getViewRoot().setLocale(new
			// Locale("en_us"));

		}

		this.cerrarModal("panelCambiarIdioma");
	}

	/**
	 * Valida un submenu
	 * 
	 * @param aTransaccion
	 * @return ok
	 */
	private boolean isValidoSubMenu(String aTransaccion) {
		boolean ok = true;

		if (aTransaccion.equals("C")) {

			if (this.isVacio(this.submenu.getTitulo())) {
				ok = false;
			} else {
				this.submenu.setTitulo(this.submenu.getTitulo().trim());
			}

			if (!this.isVacio(this.submenu.getTituloIngles())) {
				this.submenu.setTituloIngles(this.submenu.getTituloIngles().trim());
			}

			if (this.isVacio(this.submenu.getContenido())) {
				ok = false;
			} else {
				this.submenu.setContenido(this.submenu.getContenido().trim());
			}

			if (!this.isVacio(this.submenu.getContenidoIngles())) {
				this.submenu.setContenidoIngles(this.submenu.getContenidoIngles().trim());
			}

			if (!ok) {
				this.mostrarMensajeGlobal("campoEstaVacio", "advertencia");
			}

		} else {

			if (this.isVacio(this.submenuTransaccion.getTitulo())) {
				ok = false;
			} else {
				this.submenuTransaccion.setTitulo(this.submenuTransaccion.getTitulo().trim());
			}

			if (!this.isVacio(this.submenuTransaccion.getTituloIngles())) {
				this.submenuTransaccion.setTituloIngles(this.submenuTransaccion.getTituloIngles().trim());
			}

			if (this.isVacio(this.submenuTransaccion.getContenido())) {
				ok = false;
			} else {
				this.submenuTransaccion.setContenido(this.submenuTransaccion.getContenido().trim());
			}

			if (!this.isVacio(this.submenuTransaccion.getContenidoIngles())) {
				this.submenuTransaccion.setContenidoIngles(this.submenuTransaccion.getContenidoIngles().trim());
			}

		}

		return ok;
	}

	/**
	 * Determina si es válida una sección
	 * 
	 * @param aTransaccion
	 * @return ok
	 */
	private boolean isValidaSeccion(String aTransaccion) {
		boolean ok = true;

		if (this.isVacio(this.seccionTransaccion.getNombreIdentificacion())) {
			ok = false;
		} else {
			this.seccionTransaccion.setNombreIdentificacion(this.seccionTransaccion.getNombreIdentificacion().trim());
		}

		if (this.isVacio(this.seccionTransaccion.getContenido())) {
			ok = false;
		} else {
			this.seccionTransaccion.setContenido(this.seccionTransaccion.getContenido().trim());
		}

		if (!this.isVacio(this.seccionTransaccion.getContenidoIngles())) {
			this.seccionTransaccion.setContenidoIngles(this.seccionTransaccion.getContenidoIngles().trim());
		}

		return ok;
	}

	/**
	 * Valida un menu a crear
	 * 
	 * @param aTransaccion
	 * @return ok
	 */
	private boolean isValidoMenu(String aTransaccion) {
		boolean ok = true;

		if (aTransaccion.equals("C")) {

			if (this.isVacio(this.menu.getTitulo())) {
				ok = false;
			} else {
				this.menu.setTitulo(this.menu.getTitulo().trim());
			}

			if (!this.isVacio(this.menu.getTituloIngles())) {
				this.menu.setTituloIngles(this.menu.getTituloIngles().trim());
			}

			if (this.menu != null && this.menu.getPoseeContenido() != null && this.menu.getPoseeContenido().equals(IConstantes.AFIRMACION)) {
				if (this.isVacio(this.menu.getContenido())) {
					ok = false;
				} else {
					this.menu.setContenido(this.menu.getContenido().trim());
				}

				if (!this.isVacio(this.menu.getContenidoIngles())) {
					this.menu.setContenidoIngles(this.menu.getContenidoIngles().trim());
				}
			}

			if (!ok) {
				this.mostrarMensajeGlobal("campoEstaVacio", "advertencia");
			}

		} else {

			if (this.isVacio(this.menuTransaccion.getTitulo())) {
				ok = false;
			} else {
				this.menuTransaccion.setTitulo(this.menuTransaccion.getTitulo().trim());
			}

			if (!this.isVacio(this.menuTransaccion.getTituloIngles())) {
				this.menuTransaccion.setTituloIngles(this.menuTransaccion.getTituloIngles().trim());
			}

			if (this.menuTransaccion != null && this.menuTransaccion.getPoseeContenido() != null && this.menuTransaccion.getPoseeContenido().equals(IConstantes.AFIRMACION)) {
				if (this.isVacio(this.menuTransaccion.getContenido())) {
					ok = false;
				} else {
					this.menuTransaccion.setContenido(this.menuTransaccion.getContenido().trim());
				}

				if (!this.isVacio(this.menuTransaccion.getContenidoIngles())) {
					this.menuTransaccion.setContenidoIngles(this.menuTransaccion.getContenidoIngles().trim());
				}
			} else {
				this.menuTransaccion.setContenido(null);
				this.menuTransaccion.setContenidoIngles(null);

			}

		}

		return ok;
	}

	// publicos

	/**
	 * Cancela la creación de una foto
	 */
	public void cancelarFotoBaner() {

		try {
			this.fotoBaner = null;
			this.getFotoBaner();
			this.fotoBaner = null;
			this.getFotoBaner();
			this.fotosBaners = null;
			this.getFotosBaners();

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Cancela la eliminación de una foto de un baner
	 * 
	 * @param aVista
	 */
	public void cancelarFotoBanerTransaccion(String aVista) {
		try {

			this.fotoBanerTransaccion = null;
			this.getFotoBanerTransaccion();
			this.fotosBaners = null;
			this.getFotosBaners();

			if (aVista != null && aVista.equals("MODAL_ELIMINAR_FOTO_BANER")) {
				this.cerrarModal("panelEliminacionFoto");

			}
		} catch (Exception e) {

			IConstantes.log.error(e, e);

		}

	}

	/**
	 * Permite crear una foto
	 */
	public void crearFotoBaner() {
		Conexion conexion = new Conexion();

		try {
			if (isValidaFotoBaner()) {
				conexion.setAutoCommitBD(false);
				String base64DataString = Base64.getEncoder().encodeToString(this.fotoBaner.getArchivo());
				this.fotoBaner.settFotoDecodificada(base64DataString);

				this.fotoBaner.getCamposBD();
				conexion.insertarBD(this.fotoBaner.getEstructuraTabla().getTabla(), this.fotoBaner.getEstructuraTabla().getPersistencia());
				conexion.commitBD();
				this.mostrarMensajeGlobal("creacionExitosa", "exito");

				// reseteo de variables
				this.fotoBaner = null;
				this.getFotoBaner();
				this.fotosBaners = null;
				this.getFotosBaners();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Limpia la foto cargada para cargar otra
	 */
	public void limpiarFotoCargadaBaner() {
		this.fotoBaner.settFile(null);
		this.fotoBaner.setArchivo(null);
	}

	/**
	 * Recibir foto y asignara aobjeto
	 * 
	 * @param event
	 */
	public void recibirFotoBaner(FileUploadEvent event) {

		try {
			this.fotoBaner.settFile(event.getFile());
			this.fotoBaner.setArchivo(event.getFile().getContents());

			this.mostrarMensajeGlobal("archivoRecibido", "advertencia");
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Asigna una foto para ser eliminada
	 * 
	 * @param aFoto
	 * @param aVista
	 */
	public void asignarFotoBaner(Baner aFoto, String aVista) {

		try {

			this.fotoBanerTransaccion = aFoto;

			this.abrirModal("panelEliminacionFoto");

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Elimina la foto de un baner
	 */
	public void eliminarFotoBaner() {

		Conexion conexion = new Conexion();
		try {

			conexion.setAutoCommitBD(false);
			this.fotoBanerTransaccion.getCamposBD();
			conexion.eliminarBD(this.fotoBanerTransaccion.getEstructuraTabla().getTabla(), this.fotoBanerTransaccion.getEstructuraTabla().getLlavePrimaria());
			conexion.commitBD();
			this.mostrarMensajeGlobal("eliminacionExitosa", "exito");

			this.eliminarFoto(this.fotoBanerTransaccion.getId(), "fotosBaners");

			this.cerrarModal("panelEliminacionFoto");

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			this.mostrarMensajeGlobal("eliminacionFallida", "error");

		} finally {
			conexion.cerrarConexion();
		}

		// reseteo de variables
		this.fotoBanerTransaccion = null;
		this.getFotoBanerTransaccion();
		this.fotosBaners = null;
		this.getFotosBaners();

	}

	/**
	 * Permite crear una submenu para un menú
	 */
	public void crearSubMenu() {
		Conexion conexion = new Conexion();

		try {
			if (isValidoSubMenu("C")) {
				conexion.setAutoCommitBD(false);

				if (this.submenus != null && this.submenus.size() > 0) {
					this.submenu.setPosicion(this.submenus.size() + 1);
				} else {

					this.submenu.setPosicion(1);
				}
				this.submenu.setIndicativoVigencia(IConstantes.ACTIVO);
				this.submenu.getCamposBD();
				conexion.insertarBD(this.submenu.getEstructuraTabla().getTabla(), this.submenu.getEstructuraTabla().getPersistencia());
				conexion.commitBD();
				this.mostrarMensajeGlobal("creacionExitosa", "exito");

				// reseteo de variables
				this.submenu = null;
				this.getSubmenu();
				this.submenus = null;
				this.getSubmenus();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Edita un submenu
	 */
	public void editarSubMenu() {
		Conexion conexion = new Conexion();

		try {
			if (isValidoSubMenu("E")) {
				conexion.setAutoCommitBD(false);

				this.submenuTransaccion.getCamposBD();

				conexion.actualizarBD(this.submenuTransaccion.getEstructuraTabla().getTabla(), this.submenuTransaccion.getEstructuraTabla().getPersistencia(), this.submenuTransaccion.getEstructuraTabla().getLlavePrimaria(), null);

				conexion.commitBD();

				this.mostrarMensajeGlobal("edicionExitosa", "exito");
				this.cerrarModal("panelEdicionSubMenu");

				// reseteo de variables
				this.submenuTransaccion = null;
				this.getSubmenuTransaccion();
				this.submenus = null;
				this.getSubmenus();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Elimina un submenu
	 */
	public void eliminarSubMenu() {

		Conexion conexion = new Conexion();
		List<SubMenu> subs = null;
		int i = 0;
		try {

			conexion.setAutoCommitBD(false);
			this.submenuTransaccion.getCamposBD();
			conexion.eliminarBD(this.submenuTransaccion.getEstructuraTabla().getTabla(), this.submenuTransaccion.getEstructuraTabla().getLlavePrimaria());

			SubMenu sub = new SubMenu();
			sub.getMenu().setId(this.menuTransaccion.getId());
			subs = IConsultasDAO.getSubmenus(conexion, sub);
			if (subs != null && subs.size() > 0) {
				for (SubMenu p : subs) {
					i++;
					p.setPosicion(i);
					p.getCamposBD();
					conexion.actualizarBD(p.getEstructuraTabla().getTabla(), p.getEstructuraTabla().getPersistencia(), p.getEstructuraTabla().getLlavePrimaria(), null);

				}
			}

			conexion.commitBD();
			this.mostrarMensajeGlobal("eliminacionExitosa", "exito");

			this.cerrarModal("panelEliminacionSubMenu");

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			this.mostrarMensajeGlobal("eliminacionFallida", "error");

		} finally {
			conexion.cerrarConexion();
		}

		// reseteo de variables
		this.submenuTransaccion = null;
		this.getSubmenuTransaccion();
		this.submenus = null;
		this.getSubmenus();

	}

	/**
	 * Cancela un submenu
	 */
	public void cancelarSubMenu() {

		try {
			this.submenu = null;
			this.getSubmenu();
			this.submenuTransaccion = null;
			this.getSubmenuTransaccion();
			this.submenus = null;
			this.getSubmenus();

			this.cerrarModal("panelVerSubMenu");

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Cancela la edición de un submenu
	 * 
	 * @param aVista
	 */
	public void cancelarSubMenuTransaccion(String aVista) {
		try {

			this.submenuTransaccion = null;
			this.getSubmenuTransaccion();
			this.submenus = null;
			this.getSubmenus();

			if (aVista != null && aVista.equals("MODAL_EDITAR_SUBMENU")) {
				this.cerrarModal("panelEdicionSubMenu");

			} else if (aVista != null && aVista.equals("MODAL_ELIMINAR_SUBMENU")) {
				this.cerrarModal("panelEliminacionSubMenu");

			}
		} catch (Exception e) {

			IConstantes.log.error(e, e);

		}

	}

	/**
	 * Asigan un submenu para ser editado o eliminado
	 * 
	 * @param aSubMenu
	 * @param aVista
	 */
	public void asignarSubmenu(SubMenu aSubMenu, String aVista) {

		try {

			this.submenuTransaccion = aSubMenu;

			if (aVista != null && aVista.equals("MODAL_EDITAR_SUBMENU")) {

				this.abrirModal("panelEdicionSubMenu");

			} else {

				this.abrirModal("panelEliminacionSubMenu");

			}

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Reordena un submenu
	 * 
	 * @param event
	 */
	public void onRowReorderS(ReorderEvent event) {
		Conexion conexion = new Conexion();
		conexion.setAutoCommitBD(false);

		try {
			int i = 0;

			if (this.submenus != null && this.submenus.size() > 0) {

				for (SubMenu p : this.submenus) {
					i++;
					p.setPosicion(i);
					p.getCamposBD();
					conexion.actualizarBD(p.getEstructuraTabla().getTabla(), p.getEstructuraTabla().getPersistencia(), p.getEstructuraTabla().getLlavePrimaria(), null);
					conexion.commitBD();
				}
				this.submenus = null;
				this.getSubmenus();

				this.mostrarMensajeGlobalPersonalizado(this.getMensaje("subMenuMovido", "" + (event.getFromIndex() + 1), "" + (event.getToIndex() + 1)), "exito");
			}

		} catch (Exception e) {
			conexion.rollbackBD();

			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {

			conexion.cerrarConexion();
		}

	}

	/**
	 * Reordena las filas de los menus
	 * 
	 * @param event
	 */
	public void onRowReorder(ReorderEvent event) {
		Conexion conexion = new Conexion();
		conexion.setAutoCommitBD(false);

		try {
			int i = 0;

			if (this.menus != null && this.menus.size() > 0) {

				for (Menu p : this.menus) {
					i++;
					p.setPosicion(i);
					p.getCamposBD();
					conexion.actualizarBD(p.getEstructuraTabla().getTabla(), p.getEstructuraTabla().getPersistencia(), p.getEstructuraTabla().getLlavePrimaria(), null);
					conexion.commitBD();
				}
				this.menus = null;
				this.getMenus();

				this.mostrarMensajeGlobalPersonalizado(this.getMensaje("menuMovido", "" + (event.getFromIndex() + 1), "" + (event.getToIndex() + 1)), "exito");
			}

		} catch (Exception e) {
			conexion.rollbackBD();

			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {

			conexion.cerrarConexion();
		}

	}

	/**
	 * Crea un nuevo menu
	 */
	public void crearMenu() {
		Conexion conexion = new Conexion();

		try {
			if (isValidoMenu("C")) {
				conexion.setAutoCommitBD(false);

				if (this.menus != null && this.menus.size() > 0) {
					this.menu.setPosicion(this.menus.size() + 1);
				} else {

					this.menu.setPosicion(1);
				}
				this.menu.setPoseeContenido(IConstantes.NEGACION);
				this.menu.setIndicativoVigencia(IConstantes.ACTIVO);

				this.menu.getCamposBD();

				conexion.insertarBD(this.menu.getEstructuraTabla().getTabla(), this.menu.getEstructuraTabla().getPersistencia());
				conexion.commitBD();

				this.mostrarMensajeGlobal("creacionExitosa", "exito");

				// reseteo de variables
				this.menu = null;
				this.getMenu();
				this.menus = null;
				this.getMenus();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Edita un registro de sección
	 */
	public void editarSeccion() {
		Conexion conexion = new Conexion();

		try {
			if (isValidaSeccion("E")) {
				conexion.setAutoCommitBD(false);

				this.seccionTransaccion.getCamposBD();
				conexion.actualizarBD(this.seccionTransaccion.getEstructuraTabla().getTabla(), this.seccionTransaccion.getEstructuraTabla().getPersistencia(), this.seccionTransaccion.getEstructuraTabla().getLlavePrimaria(), null);
				conexion.commitBD();
				this.mostrarMensajeGlobal("edicionExitosa", "exito");

				this.cerrarModal("panelEdicionSeccion");

				// reseteo de variables
				this.seccionTransaccion = null;
				this.getSeccionTransaccion();
				this.secciones = null;
				this.getSecciones();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Edita un registro de menu en el software
	 */
	public void editarMenu() {
		Conexion conexion = new Conexion();

		try {
			if (isValidoMenu("E")) {
				conexion.setAutoCommitBD(false);
				this.menuTransaccion.setPoseeContenido(IConstantes.NEGACION);
				this.menuTransaccion.getCamposBD();
				conexion.actualizarBD(this.menuTransaccion.getEstructuraTabla().getTabla(), this.menuTransaccion.getEstructuraTabla().getPersistencia(), this.menuTransaccion.getEstructuraTabla().getLlavePrimaria(), null);
				conexion.commitBD();
				this.mostrarMensajeGlobal("edicionExitosa", "exito");

				this.cerrarModal("panelEdicionMenu");

				// reseteo de variables
				this.menuTransaccion = null;
				this.getMenuTransaccion();
				this.menus = null;
				this.getMenus();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Elimina un registro de menu
	 */
	public void eliminarMenu() {
		int i = 0;
		Conexion conexion = new Conexion();
		try {

			conexion.setAutoCommitBD(false);
			this.menuTransaccion.getCamposBD();

			conexion.eliminarBD(this.menuTransaccion.getEstructuraTabla().getTabla(), this.menuTransaccion.getEstructuraTabla().getLlavePrimaria());

			List<Menu> menusNuevos = IConsultasDAO.getMenus(conexion);
			if (menusNuevos != null && menusNuevos.size() > 0) {
				for (Menu p : menusNuevos) {
					i++;
					p.setPosicion(i);
					p.getCamposBD();
					conexion.actualizarBD(p.getEstructuraTabla().getTabla(), p.getEstructuraTabla().getPersistencia(), p.getEstructuraTabla().getLlavePrimaria(), null);

				}
			}

			conexion.commitBD();
			this.mostrarMensajeGlobal("eliminacionExitosa", "exito");

			this.cerrarModal("panelEliminacionMenu");

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			this.mostrarMensajeGlobal("eliminacionFallida", "error");

		} finally {
			conexion.cerrarConexion();
		}

		// reseteo de variables
		this.menuTransaccion = null;
		this.menus = null;
		this.getMenus();

	}

	/**
	 * Este método borra el formulario de creación de un menu
	 */
	public void cancelarMenu() {

		try {
			this.menu = new Menu();

			this.menus = null;
			this.getMenus();
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Este método borra el formulario de edición de un menu en transacción
	 */
	public void cancelarMenuTransaccion(String aVista) {
		try {

			this.menuTransaccion = new Menu();
			this.menus = null;
			this.getMenus();

			if (aVista != null && aVista.equals("MODAL_EDITAR_MENU")) {
				this.cerrarModal("panelEdicionMenu");

			} else if (aVista != null && aVista.equals("MODAL_ELIMINAR_MENU")) {
				this.cerrarModal("panelEliminacionMenu");

			}

		} catch (Exception e) {

			IConstantes.log.error(e, e);

		}

	}

	/**
	 * Cancela la edición de una sección
	 * 
	 * @param aVista
	 */
	public void cancelarSeccionTransaccion(String aVista) {
		try {

			this.seccionTransaccion = new Seccion();
			this.secciones = null;
			this.getSecciones();

			if (aVista != null && aVista.equals("MODAL_EDITAR_SECCION")) {
				this.cerrarModal("panelEdicionSeccion");

			}

		} catch (Exception e) {

			IConstantes.log.error(e, e);

		}

	}

	/**
	 * Asiganuna sección para ser ediatada
	 * 
	 * @param aSeccion
	 * @param aVista
	 */
	public void asignarSeccion(Seccion aSeccion, String aVista) {

		try {

			this.seccionTransaccion = aSeccion;

			if (aVista != null && aVista.equals("MODAL_EDITAR_SECCION")) {

				this.abrirModal("panelEdicionSeccion");

			}

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Asigna un menu para realizar una acción
	 * 
	 * @param aMenu
	 * @param aVista
	 */
	public void asignarMenu(Menu aMenu, String aVista) {

		try {

			this.menuTransaccion = aMenu;

			if (aVista != null && aVista.equals("MODAL_EDITAR_MENU")) {

				this.submenu = null;
				this.getSubmenu();
				this.submenuTransaccion = null;
				this.getSubmenuTransaccion();
				this.submenus = null;
				this.getSubmenus();

				this.abrirModal("panelEdicionMenu");

			} else if (aVista != null && aVista.equals("MODAL_VER_SUBMENUS")) {

				this.submenu = null;
				this.getSubmenu();
				this.submenuTransaccion = null;
				this.getSubmenuTransaccion();
				this.submenus = null;
				this.getSubmenus();

				this.abrirModal("panelVerSubMenu");

			} else {

				this.abrirModal("panelEliminacionMenu");

			}

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	public Menu getMenu() {
		try {
			if (this.menu == null) {
				this.menu = new Menu();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenuTransaccion() {
		try {
			if (this.menuTransaccion == null) {
				this.menuTransaccion = new Menu();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return menuTransaccion;
	}

	public Seccion getSeccionTransaccion() {
		try {
			if (this.seccionTransaccion == null) {
				this.seccionTransaccion = new Seccion();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return seccionTransaccion;
	}

	public void setSeccionTransaccion(Seccion seccionTransaccion) {
		this.seccionTransaccion = seccionTransaccion;
	}

	public void setMenuTransaccion(Menu menuTransaccion) {
		this.menuTransaccion = menuTransaccion;
	}

	public SubMenu getSubmenu() {
		try {
			if (this.submenu == null) {
				this.submenu = new SubMenu();
				this.submenu.getMenu().setId(this.menuTransaccion.getId());
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return submenu;
	}

	public void setSubmenu(SubMenu submenu) {
		this.submenu = submenu;
	}

	public SubMenu getSubmenuTransaccion() {
		try {
			if (this.submenuTransaccion == null) {
				this.submenuTransaccion = new SubMenu();
				this.submenuTransaccion.getMenu().setId(this.menuTransaccion.getId());
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return submenuTransaccion;
	}

	public void setSubmenuTransaccion(SubMenu submenuTransaccion) {
		this.submenuTransaccion = submenuTransaccion;
	}

	public List<Menu> getMenus() {
		try {
			if (this.menus == null) {
				Menu menu = null;
				this.menus = IConsultasDAO.getMenus(menu);

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Menu> getMenusActivos() {
		try {
			if (this.menusActivos == null) {
				Menu menu = new Menu();
				menu.setIndicativoVigencia(IConstantes.ACTIVO);
				this.menusActivos = IConsultasDAO.getMenus(menu);

				if (this.menusActivos != null && this.menusActivos.size() > 0) {
					for (Menu m : this.menusActivos) {
						m.settSubmenus(new ArrayList<SubMenu>());
						SubMenu submenu = new SubMenu();
						submenu.getMenu().setId(m.getId());
						submenu.setIndicativoVigencia(IConstantes.ACTIVO);
						m.settSubmenus(IConsultasDAO.getSubmenus(submenu));
					}

				}
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return menusActivos;
	}

	public void setMenusActivos(List<Menu> menusActivos) {

		this.menusActivos = menusActivos;
	}

	public List<Seccion> getSecciones() {
		try {
			if (this.secciones == null) {

				this.secciones = IConsultasDAO.getSecciones(null);

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return secciones;
	}

	public void setSecciones(List<Seccion> secciones) {
		this.secciones = secciones;
	}

	public List<SubMenu> getSubmenus() {
		try {
			if (this.submenus == null && this.menuTransaccion != null && this.menuTransaccion.getId() != null) {
				SubMenu submenu = new SubMenu();
				submenu.getMenu().setId(this.menuTransaccion.getId());
				this.submenus = IConsultasDAO.getSubmenus(submenu);

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return submenus;
	}

	public void setSubmenus(List<SubMenu> submenus) {
		this.submenus = submenus;
	}

	public Baner getFotoBaner() {
		try {
			if (this.fotoBaner == null) {
				this.fotoBaner = new Baner();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return fotoBaner;
	}

	public void setFotoBaner(Baner fotoBaner) {
		this.fotoBaner = fotoBaner;
	}

	public Baner getFotoBanerTransaccion() {
		try {
			if (this.fotoBanerTransaccion == null) {
				this.fotoBanerTransaccion = new Baner();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return fotoBanerTransaccion;
	}

	public void setFotoBanerTransaccion(Baner fotoBanerTransaccion) {
		this.fotoBanerTransaccion = fotoBanerTransaccion;
	}

	/**
	 * Obtiene un listado de fotos y las guarad en disco
	 * 
	 * @return fotosBaners
	 */
	public List<Baner> getFotosBaners() {
		try {
			if (this.fotosBaners == null) {

				this.fotosBaners = IConsultasDAO.getFotosBaners(null);

				// if (this.fotosBaners != null && this.fotosBaners.size() > 0) {
				// for (Baner f : this.fotosBaners) {
				// this.guardarImagenEnDisco(f.getId(), f.gettFotoDecodificada(),
				// "fotosBaners");
				// f.settNombreFoto("foto" + f.getId() + ".jpg");
				//
				// }
				// }

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return fotosBaners;
	}

	public void setFotosBaners(List<Baner> fotosBaners) {
		this.fotosBaners = fotosBaners;
	}

	public String getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(String idSeccion) {
		this.idSeccion = idSeccion;
	}

	public Menu getMenuConsultado() {
		try {
			this.menuConsultado = null;
			if (this.idSeccion != null && !this.idSeccion.trim().equals("")) {
				Menu menu = new Menu();
				menu.setId(Integer.parseInt(this.idSeccion));
				List<Menu> menus = IConsultasDAO.getMenus(menu);
				if (menus != null && menus.size() > 0) {
					this.menuConsultado = menus.get(0);
				}
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return menuConsultado;
	}

	public void setMenuConsultado(Menu menuConsultado) {
		this.menuConsultado = menuConsultado;
	}

	public SubMenu getSubMenuConsultado() {
		try {
			this.subMenuConsultado = null;
			if (this.idSeccion != null && !this.idSeccion.trim().equals("")) {
				SubMenu submenu = new SubMenu();
				submenu.setId(Integer.parseInt(this.idSeccion));
				List<SubMenu> submenus = IConsultasDAO.getSubmenus(submenu);
				if (submenus != null && submenus.size() > 0) {
					this.subMenuConsultado = submenus.get(0);
				}
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return subMenuConsultado;
	}

	public void setSubMenuConsultado(SubMenu subMenuConsultado) {
		this.subMenuConsultado = subMenuConsultado;
	}

	/**
	 * Limpia el archivo cargado para volver a ingresar otro
	 */
	public void limpiarArchivoCargado2() {
		this.hojaVida.settFile(null);
		this.hojaVida.setArchivo(null);
	}

	/**
	 * Limpia el archivo cargado para volver a ingresar otro
	 */
	public void limpiarArchivoCargado3() {
		this.planillaje.settFile(null);
		this.planillaje.setArchivo(null);
	}

	/**
	 * Limpia el archivo cargado para volver a ingresar otro
	 */
	public void limpiarArchivoCargado() {
		this.pqr.settFile(null);
		this.pqr.setArchivo(null);
	}

	private boolean isValidoAdjunto2() {
		boolean ok = true;

		if (this.hojaVida.getArchivo() == null) {
			ok = false;
			this.mostrarMensajeGlobal("archivoAdjuntoRequerido", "advertencia");
		}

		return ok;
	}

	private boolean isValidoAdjunto3() {
		boolean ok = true;

		if (this.planillaje.gettPoseeAdjunto().equals(IConstantes.AFIRMACION)) {

			if (this.planillaje.getArchivo() == null) {
				ok = false;
				this.mostrarMensajeGlobal("archivoAdjuntoRequerido", "advertencia");
			}

		} else {
			this.planillaje.setArchivo(null);
			this.planillaje.setContentType(null);
			this.planillaje.setExtension(null);

		}

		return ok;
	}

	private boolean isValidoAdjunto() {
		boolean ok = true;

		if (this.pqr.gettPoseeAdjunto().equals(IConstantes.AFIRMACION)) {

			if (this.pqr.getArchivo() == null) {
				ok = false;
				this.mostrarMensajeGlobal("archivoAdjuntoRequerido", "advertencia");
			}

		} else {
			this.pqr.setArchivo(null);
			this.pqr.setContentType(null);
			this.pqr.setExtension(null);

		}

		return ok;
	}

	/**
	 * Envía contactenos
	 */
	public void enviarContactenos() {
		String area = "";

		List<Administrador> administradores = null;
		List<String> correos = null;
		String correo = "NO POSEE";

		List<Area> areas = null;

		int i = 0;
		try {

			Area temp = new Area();
			temp.setId(this.cotactenos.getArea().getId());
			areas = IConsultasDAO.getAreas(temp);

			administradores = IConsultasDAO.getAdministradores(null);
			correos = new ArrayList<String>();
			for (Administrador a : administradores) {
				if (a.getArea().getId().intValue() == this.cotactenos.getArea().getId().intValue() && a.getEstadoVigencia() != null && a.getEstadoVigencia().equals("A")) {
					correos.add(a.getCorreoElectronico());
					i++;
					if (i >= 100) {
						break;
					}
				}
			}

			if (areas != null && areas.size() > 0) {
				area = areas.get(0).getNombre();

				if (!(correos != null && correos.size() > 0)) {
					area = area + " " + this.getMensaje("noSeEnvio");
				}

			} else {
				area = "" + this.getMensaje("noListadoFavorRemitir");
			}

			if (i == 0) {

				// significa que no encontró a nadie con ese perfil por tanto lo
				// envía a los adm de contenido
				correos = new ArrayList<String>();
				for (Administrador a : administradores) {
					if (a.isCambiarContenido() && a.getEstadoVigencia().equals("A")) {
						correos.add(a.getCorreoElectronico());
						i++;
						if (i >= 100) {
							break;
						}
					}
				}

			}

			// NOMBRE
			String nombreContactenos = this.getMensaje("nuevoMensaje") + "-" + this.cotactenos.getFechaArchivo();

			// mensaje al cliente si puso
			if (this.cotactenos.getCorreo() != null && !this.cotactenos.getCorreo().trim().equals("")) {
				correo = this.cotactenos.getCorreo().trim();
				IEmail.enviarCorreo(this.getMensaje("mensajeContactenosCliente", "" + this.cotactenos.getNombres(), "" + this.cotactenos.getFechaArchivo()), nombreContactenos, correo);
			}

			if (correos != null && correos.size() > 0) {

				String mensajeFuncionario = this.getMensaje("mensajeContactenosVijagual", "" + nombreContactenos, "" + this.cotactenos.getFecha(), "" + area, "" + this.cotactenos.getNombres(), "" + this.cotactenos.getTelefonos(), "" + correo, "" + this.cotactenos.getAsunto(), this.cotactenos.getDescripcion());

				// correo sin adjunto
				IEmail.enviarCorreoMasivo(mensajeFuncionario, nombreContactenos, correos);

			}

			this.mostrarMensajeGlobal("contactenosEnviado", "exito");

			this.cotactenos = null;
			this.getCotactenos();

		} catch (Exception e) {
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			IConstantes.log.error(e, e);
		}

	}

	public void enviarHojaVida() {
		String area = "";

		List<Administrador> administradores = null;
		List<String> correos = null;
		String correo = "NO POSEE";

		int i = 0;
		try {
			if (isValidoAdjunto2()) {

				administradores = IConsultasDAO.getAdministradores(null);
				correos = new ArrayList<String>();
				for (Administrador a : administradores) {
					if (a.isRecibirHojasVida() && a.getEstadoVigencia().equals("A")) {
						correos.add(a.getCorreoElectronico());
						i++;
						if (i >= 100) {
							break;
						}
					}
				}

				if (!(correos != null && correos.size() > 0)) {
					area = area + " " + this.getMensaje("noSeEnvio2");
				}

				if (i == 0) {

					// significa que no encontró a nadie con ese perfil por tanto lo
					// envía a los adm de contenido
					correos = new ArrayList<String>();
					for (Administrador a : administradores) {
						if (a.isCambiarContenido() && a.getEstadoVigencia().equals("A")) {
							correos.add(a.getCorreoElectronico());
							i++;
							if (i >= 100) {
								break;
							}
						}
					}

				}

				// NOMBREPQR
				String nombrePqr = "HV-" + this.hojaVida.getFechaArchivo();

				// mensaje al cliente si puso
				if (this.hojaVida.getCorreo() != null && !this.hojaVida.getCorreo().trim().equals("")) {
					correo = this.hojaVida.getCorreo().trim();
					IEmail.enviarCorreo(this.getMensaje("mensajeHvCliente", "" + this.hojaVida.getNombres(), "" + nombrePqr), nombrePqr, correo);
				}

				if (correos != null && correos.size() > 0) {

					String mensajeFuncionario = this.getMensaje("mensajeHvVijagual", "" + nombrePqr, "" + this.hojaVida.getFecha(), "" + this.hojaVida.getCedula(), "" + this.hojaVida.getNombres(), "" + this.hojaVida.getTelefonos(), "" + this.hojaVida.getCorreo(), "" + this.hojaVida.getCiudad(), this.hojaVida.getDescripcion(), area);

					// correo con adjunto
					String nombreAdjunto = "HV-ADJUNTA-" + this.hojaVida.getFechaArchivo() + "." + this.hojaVida.getExtension();

					String path = this.getPath("/archivosTemporales/") + "/" + nombreAdjunto;

					OutputStream out = new FileOutputStream(path);
					out.write(this.hojaVida.getArchivo());
					out.close();

					IEmail.enviarCorreoMasivoAdjunto(mensajeFuncionario, nombrePqr, correos, path, nombreAdjunto);

				}

				this.mostrarMensajeGlobal("hvEnviada", "exito");

				this.hojaVida = null;
				this.getHojaVida();

			}

		} catch (Exception e) {
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			IConstantes.log.error(e, e);
		}

	}

	public void enviarPlanillaje() {
		String area = "";
		String poseeAdjunto = "";
		List<Administrador> administradores = null;
		List<String> correos = null;
		String correo = "NO POSEE";

		List<Area> areas = null;

		int i = 0;
		try {
			if (isValidoAdjunto3()) {

				administradores = IConsultasDAO.getAdministradores(null);
				correos = new ArrayList<String>();
				for (Administrador a : administradores) {
					if (a.isRecibirPlanillaje() && a.getEstadoVigencia() != null && a.getEstadoVigencia().equals("A")) {
						correos.add(a.getCorreoElectronico());
						i++;
						if (i >= 100) {
							break;
						}
					}
				}

				if (!(correos != null && correos.size() > 0)) {
					area = area + " " + this.getMensaje("noSeEnvio3");
				}

				if (i == 0) {

					// significa que no encontró a nadie con ese perfil por tanto lo
					// envía a los adm de contenido
					correos = new ArrayList<String>();
					for (Administrador a : administradores) {
						if (a.isCambiarContenido() && a.getEstadoVigencia().equals("A")) {
							correos.add(a.getCorreoElectronico());
							i++;
							if (i >= 100) {
								break;
							}
						}
					}

				}

				// NOMBREPQR
				String nombrePqr = "PLANILLAJE-" + this.planillaje.getFechaArchivo();

				// mensaje al cliente si puso
				if (this.planillaje.getClientePlanillaje().getCorreo() != null && !this.planillaje.getClientePlanillaje().getCorreo().trim().equals("")) {
					correo = this.planillaje.getClientePlanillaje().getCorreo().trim();
					IEmail.enviarCorreo(this.getMensaje("mensajePlanillajeCliente", "" + this.planillaje.getClientePlanillaje().getNombres(), "" + nombrePqr), nombrePqr, correo);
				}

				if (correos != null && correos.size() > 0) {

					if (this.planillaje.gettPoseeAdjunto().equals("S")) {
						poseeAdjunto = "SI";

						String tabla = "";

						tabla += "<table width='100%' border='1' cellspacing='0' cellpadding='2'>";
						tabla += "<tr>";
						tabla += "<td>&nbsp;</td>";
						tabla += "<td>Animal Número</td>";
						tabla += "<td>Sexo</td>";
						tabla += "<td>Especie</td>";
						tabla += "<td>Peso del animal vivo</td>";
						tabla += "<td>Corral actual</td>";
						tabla += "<td>Puesto de carne 1</td>";
						tabla += "<td>Puesto de carne 2</td>";
						tabla += "<td>Puesto de viscera</td>";
						tabla += "<td>Destino de la piel</td>";
						tabla += "</tr>";

						int y = 0;
						for (Planillaje p : this.planillajes) {

							tabla += "<tr>";
							tabla += "<td>" + this.getLetra(y) + "</td>";
							tabla += "<td>" + p.getAnimal() + "</td>";
							tabla += "<td>" + p.getSexo() + "</td>";
							tabla += "<td>" + p.getEspecie() + "</td>";
							tabla += "<td>" + p.getPesoVivo() + "</td>";
							tabla += "<td>" + p.getCorralActual() + "</td>";
							tabla += "<td>" + p.getPuestoCarne1() + "</td>";
							tabla += "<td>" + p.getPuestoCarne2() + "</td>";
							tabla += "<td>" + p.getPuestoViscera() + "</td>";
							tabla += "<td>" + p.getDestinoPiel() + "</td>";
							tabla += "</tr>";
							y++;
						}

						tabla += "</table>";

						String mensajeFuncionario = this.getMensaje("mensajePlanillajeVijagual", "" + nombrePqr, "" + this.planillaje.getFecha(), "" + this.planillaje.getClientePlanillaje().getCedula(), "" + this.planillaje.getClientePlanillaje().getNombres(), "" + this.planillaje.getClientePlanillaje().getTelefonos(), "" + this.planillaje.getClientePlanillaje().getCorreo(), tabla, "" + poseeAdjunto, area);

						// correo con adjunto
						String nombreAdjunto = "ADJUNTO-PLANILLAJE-" + this.planillaje.getFechaArchivo() + "." + this.planillaje.getExtension();

						String path = this.getPath("/archivosTemporales/") + "/" + nombreAdjunto;

						OutputStream out = new FileOutputStream(path);
						out.write(this.planillaje.getArchivo());
						out.close();

						IEmail.enviarCorreoMasivoAdjunto(mensajeFuncionario, nombrePqr, correos, path, nombreAdjunto);

					} else {

						poseeAdjunto = "NO";

						String tabla = "";

						tabla += "<table width='100%' border='1' cellspacing='0' cellpadding='2'>";
						tabla += "<tr>";
						tabla += "<td>&nbsp;</td>";
						tabla += "<td>Animal Número</td>";
						tabla += "<td>Sexo</td>";
						tabla += "<td>Especie</td>";
						tabla += "<td>Peso del animal vivo</td>";
						tabla += "<td>Corral actual</td>";
						tabla += "<td>Puesto de carne 1</td>";
						tabla += "<td>Puesto de carne 2</td>";
						tabla += "<td>Puesto de viscera</td>";
						tabla += "<td>Destino de la piel</td>";
						tabla += "</tr>";

						int y = 0;
						for (Planillaje p : this.planillajes) {

							tabla += "<tr>";
							tabla += "<td>" + this.getLetra(y) + "</td>";
							tabla += "<td>" + p.getAnimal() + "</td>";
							tabla += "<td>" + p.getSexo() + "</td>";
							tabla += "<td>" + p.getEspecie() + "</td>";
							tabla += "<td>" + p.getPesoVivo() + "</td>";
							tabla += "<td>" + p.getCorralActual() + "</td>";
							tabla += "<td>" + p.getPuestoCarne1() + "</td>";
							tabla += "<td>" + p.getPuestoCarne2() + "</td>";
							tabla += "<td>" + p.getPuestoViscera() + "</td>";
							tabla += "<td>" + p.getDestinoPiel() + "</td>";
							tabla += "</tr>";
							y++;
						}

						tabla += "</table>";

						String mensajeFuncionario = this.getMensaje("mensajePlanillajeVijagual", "" + nombrePqr, "" + this.planillaje.getFecha(), "" + this.planillaje.getClientePlanillaje().getCedula(), "" + this.planillaje.getClientePlanillaje().getNombres(), "" + this.planillaje.getClientePlanillaje().getTelefonos(), "" + this.planillaje.getClientePlanillaje().getCorreo(), tabla, "" + poseeAdjunto, area);

						// correo sin adjunto
						IEmail.enviarCorreoMasivo(mensajeFuncionario, nombrePqr, correos);

					}

				}

				this.mostrarMensajeGlobal("planillajeEnviada", "exito");

				this.planillaje = null;
				this.getPlanillaje();

			}

		} catch (Exception e) {
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Envía pqrs
	 */
	public void enviarPqrs() {
		String area = "";
		String poseeAdjunto = "";
		List<Administrador> administradores = null;
		List<String> correos = null;
		String correo = "NO POSEE";

		List<Area> areas = null;

		int i = 0;
		try {
			if (isValidoAdjunto()) {

				Area temp = new Area();
				temp.setId(this.pqr.getArea().getId());
				areas = IConsultasDAO.getAreas(temp);

				administradores = IConsultasDAO.getAdministradores(null);
				correos = new ArrayList<String>();
				for (Administrador a : administradores) {
					if (a.getArea().getId().intValue() == this.pqr.getArea().getId().intValue() && a.getEstadoVigencia() != null && a.getEstadoVigencia().equals("A")) {
						correos.add(a.getCorreoElectronico());
						i++;
						if (i >= 100) {
							break;
						}
					}
				}

				if (areas != null && areas.size() > 0) {
					area = areas.get(0).getNombre();

					if (!(correos != null && correos.size() > 0)) {
						area = area + " " + this.getMensaje("noSeEnvio");
					}

				} else {
					area = "" + this.getMensaje("noListadoFavorRemitir");
				}

				if (i == 0) {

					// significa que no encontró a nadie con ese perfil por tanto lo
					// envía a los adm de contenido
					correos = new ArrayList<String>();
					for (Administrador a : administradores) {
						if (a.isCambiarContenido() && a.getEstadoVigencia().equals("A")) {
							correos.add(a.getCorreoElectronico());
							i++;
							if (i >= 100) {
								break;
							}
						}
					}

				}

				// NOMBREPQR
				String nombrePqr = "PQR-" + this.pqr.getFechaArchivo();

				// mensaje al cliente si puso
				if (this.pqr.getCorreo() != null && !this.pqr.getCorreo().trim().equals("")) {
					correo = this.pqr.getCorreo().trim();
					IEmail.enviarCorreo(this.getMensaje("mensajePqrCliente", "" + this.pqr.getNombres(), "" + nombrePqr), nombrePqr, correo);
				}

				if (correos != null && correos.size() > 0) {

					if (this.pqr.gettPoseeAdjunto().equals("S")) {
						poseeAdjunto = "SI";
						String mensajeFuncionario = this.getMensaje("mensajePqrVijagual", "" + nombrePqr, "" + this.pqr.getFecha(), "" + this.pqr.getTipoCliente(), "" + this.pqr.getTipoPqr(), "" + area, "" + this.pqr.getNombres(), "" + this.pqr.getCedula(), "" + this.pqr.getTelefonos(), "" + this.pqr.getCiudad(), "" + this.pqr.getDireccion(), "" + correo, "" + poseeAdjunto, this.pqr.getDescripcion());

						// correo con adjunto
						String nombreAdjunto = "ADJUNTO-PQR-" + this.pqr.getFechaArchivo() + "." + this.pqr.getExtension();

						String path = this.getPath("/archivosTemporales/") + "/" + nombreAdjunto;

						OutputStream out = new FileOutputStream(path);
						out.write(this.pqr.getArchivo());
						out.close();

						IEmail.enviarCorreoMasivoAdjunto(mensajeFuncionario, nombrePqr, correos, path, nombreAdjunto);

					} else {

						poseeAdjunto = "NO";
						String mensajeFuncionario = this.getMensaje("mensajePqrVijagual", "" + nombrePqr, "" + this.pqr.getFecha(), "" + this.pqr.getTipoCliente(), "" + this.pqr.getTipoPqr(), "" + area, "" + this.pqr.getNombres(), "" + this.pqr.getCedula(), "" + this.pqr.getTelefonos(), "" + this.pqr.getCiudad(), "" + this.pqr.getDireccion(), "" + correo, "" + poseeAdjunto, this.pqr.getDescripcion());

						// correo sin adjunto
						IEmail.enviarCorreoMasivo(mensajeFuncionario, nombrePqr, correos);

					}

				}

				this.mostrarMensajeGlobal("pqrEnviada", "exito");

				this.pqr = null;
				this.getPqr();

			}

		} catch (Exception e) {
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Obtiene el tipo de archivo
	 * 
	 * @param aNombre
	 * @return tipo
	 */
	private String getTipoArchivo(String aNombre) {
		String tipo = "";
		int ultimoPunto = 0;
		try {

			ultimoPunto = aNombre.lastIndexOf('.');
			tipo = aNombre.substring(ultimoPunto + 1);

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return tipo;
	}

	/**
	 * Recibir el archivo y lo asigna al objeto
	 * 
	 * @param event
	 */
	public void recibirArchivo2(FileUploadEvent event) {

		try {
			this.hojaVida.settFile(event.getFile());
			this.hojaVida.setArchivo(event.getFile().getContents());
			this.hojaVida.setContentType(event.getFile().getContentType());
			this.hojaVida.setExtension(this.getTipoArchivo(event.getFile().getFileName()));

			this.mostrarMensajeGlobal(this.getMensaje("archivoRecibidoPqrs"), "advertencia");
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	public void recibirArchivo3(FileUploadEvent event) {

		try {
			this.planillaje.settFile(event.getFile());
			this.planillaje.setArchivo(event.getFile().getContents());
			this.planillaje.setContentType(event.getFile().getContentType());
			this.planillaje.setExtension(this.getTipoArchivo(event.getFile().getFileName()));

			this.mostrarMensajeGlobal(this.getMensaje("archivoRecibidoPqrs"), "advertencia");
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Recibir el archivo y lo asigna al objeto
	 * 
	 * @param event
	 */
	public void recibirArchivo(FileUploadEvent event) {

		try {
			this.pqr.settFile(event.getFile());
			this.pqr.setArchivo(event.getFile().getContents());
			this.pqr.setContentType(event.getFile().getContentType());
			this.pqr.setExtension(this.getTipoArchivo(event.getFile().getFileName()));

			this.mostrarMensajeGlobal(this.getMensaje("archivoRecibidoPqrs"), "advertencia");
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	public Pqr getPqr() {
		try {

			if (this.pqr == null) {
				this.pqr = new Pqr();

				Date localTime = new Date();
				DateFormat converter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
				DateFormat converter2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				converter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
				this.pqr.setFecha("" + converter.format(localTime));
				this.pqr.setFechaArchivo("" + converter2.format(localTime));

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return pqr;
	}

	public void setPqr(Pqr pqr) {
		this.pqr = pqr;
	}

	public Planillaje getPlanillaje() {
		try {

			if (this.planillaje == null) {
				this.planillaje = new Planillaje();

				Date localTime = new Date();
				DateFormat converter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
				DateFormat converter2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				converter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
				this.planillaje.setFecha("" + converter.format(localTime));
				this.planillaje.setFechaArchivo("" + converter2.format(localTime));

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return planillaje;
	}

	public void setPlanillaje(Planillaje planillaje) {
		this.planillaje = planillaje;
	}

	public Contactenos getCotactenos() {
		try {

			if (this.cotactenos == null) {
				this.cotactenos = new Contactenos();

				Date localTime = new Date();
				DateFormat converter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
				DateFormat converter2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				converter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
				this.cotactenos.setFecha("" + converter.format(localTime));
				this.cotactenos.setFechaArchivo("" + converter2.format(localTime));

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return cotactenos;
	}

	public void setCotactenos(Contactenos cotactenos) {
		this.cotactenos = cotactenos;
	}

	public HojaVida getHojaVida() {
		try {

			if (this.hojaVida == null) {
				this.hojaVida = new HojaVida();

				Date localTime = new Date();
				DateFormat converter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
				DateFormat converter2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				converter.setTimeZone(TimeZone.getTimeZone("GMT-5"));
				this.hojaVida.setFecha("" + converter.format(localTime));
				this.hojaVida.setFechaArchivo("" + converter2.format(localTime));

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return hojaVida;
	}

	public void setHojaVida(HojaVida hojaVida) {
		this.hojaVida = hojaVida;
	}

	public String getLetra(Integer numero) {

		return "" + (char) ('A' + numero) + ")";

	}

	/**
	 * Quita la planilla
	 * 
	 * @param aPlanilla
	 */
	public void quitarPlanilla(Planillaje aPlanilla) {

		try {
			this.planillajes.remove(aPlanilla);

			this.mostrarMensajeGlobal("eliminoAnimal", "advertencia");

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
	}

	public void agregarPlanilla() {
		try {
			Planillaje planilla = new Planillaje();
			this.planillajes.add(planilla);
			this.mostrarMensajeGlobal("agergoAnimal", "advertencia");
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	public List<Planillaje> getPlanillajes() {
		try {

			if (this.planillajes == null) {

				this.planillajes = new ArrayList<Planillaje>();
				Planillaje planilla = new Planillaje();
				this.planillajes.add(planilla);

			}

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

		return planillajes;
	}

	public void setPlanillajes(List<Planillaje> planillajes) {
		this.planillajes = planillajes;
	}

}
