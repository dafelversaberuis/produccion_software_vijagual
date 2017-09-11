package web.modulos.personal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import web.Conexion;
import web.beans.Administrador;
import web.beans.Area;
import web.generales.ConsultarFuncionesAPI;
import web.generales.IConstantes;
import web.generales.IEmail;
import web.modulos.IConsultasDAO;

@ManagedBean
@ViewScoped
public class AdministrarRol extends ConsultarFuncionesAPI implements Serializable {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -3213486510522513556L;
	private Administrador				administrador;
	private Administrador				administradorTransaccion;

	private List<Administrador>	administradores;

	private List<SelectItem>		itemsAreas;
	// privados

	/**
	 * Obtiene una clave aleatoria numérica de n dígitos
	 * 
	 * @return clave
	 */
	public String getClaveAleatoria() {
		String clave = "";
		int n = 0;
		try {
			for (int i = 1; i <= IConstantes.NUMERO_DIGITOS_CLAVE_ALEATORIA.intValue(); i++) {
				n = (int) (10.0 * Math.random()) + 0;
				clave = clave + String.valueOf(n);

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return clave;

	}

	/**
	 * Valida un administrador
	 * 
	 * @param aTransaccion
	 * @return ok
	 */
	private boolean isValidoAdministrador(String aTransaccion) {
		boolean ok = true;

		if (aTransaccion.equals("C")) {
			if (this.administradores != null && this.administradores.size() > 0 && this.administradores.stream().anyMatch(i -> i.getCorreoElectronico().trim().toLowerCase().equals(this.administrador.getCorreoElectronico().trim().toLowerCase()))) {
				ok = false;
				this.mostrarMensajeGlobal("correoExistenteAdministrador", "advertencia");
			}
			if (this.isVacio(this.administrador.getNombres())) {
				ok = false;
				this.mostrarMensajeGlobal("nombresVacios", "advertencia");
			}

			if (this.isVacio(this.administrador.getCorreoElectronico())) {
				ok = false;
				this.mostrarMensajeGlobal("correoVacio", "advertencia");
			}

		} else {

			if (this.administradores != null && this.administradores.size() > 0 && this.administradores.stream().anyMatch(i -> i.getId() != this.administradorTransaccion.getId() && i.getCorreoElectronico().trim().toLowerCase().equals(this.administradorTransaccion.getCorreoElectronico().trim().toLowerCase()))) {
				ok = false;
				this.mostrarMensajeGlobal("correoExistenteAdministrador", "advertencia");
			}

			if (this.isVacio(this.administradorTransaccion.getNombres())) {
				ok = false;
				this.mostrarMensajeGlobal("nombresVacios", "advertencia");
			}

			if (this.isVacio(this.administradorTransaccion.getCorreoElectronico())) {
				ok = false;
				this.mostrarMensajeGlobal("correoVacio", "advertencia");
			}

		}

		return ok;
	}

	// publicos

	/**
	 * Obtiene los permisos
	 * 
	 * @param aAdministrador
	 * @return permisos
	 */
	public String getPermisos(Administrador aAdministrador) {
		String permisos = "";
		if (aAdministrador != null && aAdministrador.isCambiarContenido()) {
			permisos += "" + this.getMensaje("cambiarContenido");
		}

		if (aAdministrador != null && aAdministrador.isRecibirHojasVida()) {
			if (!permisos.trim().equals("")) {
				permisos += ", ";
			}
			permisos += "" + this.getMensaje("recibirHojasVida");
		}

		if (aAdministrador != null && aAdministrador.isRecibirPlanillaje()) {
			if (!permisos.trim().equals("")) {
				permisos += ", ";
			}
			permisos += "" + this.getMensaje("recibirPlanillaje");
		}

		return permisos;

	}

	/**
	 * Crea un nuevo administrador del software
	 */
	public void crearAdministrador() {
		Conexion conexion = new Conexion();

		try {
			if (isValidoAdministrador("C")) {
				conexion.setAutoCommitBD(false);

				this.administrador.setEstadoVigencia(IConstantes.ACTIVO);
				this.administrador.setNombres(this.getSinEspacios(this.administrador.getNombres()));

				this.administrador.setCorreoElectronico(this.administrador.getCorreoElectronico().trim());
				this.administrador.setClave(this.getClaveAleatoria());

				this.administrador.getCamposBD();

				conexion.insertarBD(this.administrador.getEstructuraTabla().getTabla(), this.administrador.getEstructuraTabla().getPersistencia());
				conexion.commitBD();

				this.mostrarMensajeGlobal("creacionExitosa", "exito");

				if (this.administrador.isCambiarContenido()) {
					this.mostrarMensajeGlobalPersonalizado(this.getMensaje("claveAleatoria", this.administrador.getClave()), "exito");

					IEmail.enviarCorreo(this.getMensaje("mensajeClaveAleatoria", this.administrador.getNombres(), this.administrador.getClave()), this.getMensaje("asuntoClaveAleatoria"), this.administrador.getCorreoElectronico());
					this.mostrarMensajeGlobalPersonalizado(this.getMensaje("claveCorreoExitoso", this.administrador.getCorreoElectronico()), "exito");
				}

				// reseteo de variables
				this.administrador = null;
				this.getAdministrador();
				this.administradores = null;
				this.getAdministradores();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Genera una nueva clave aleatoria para el administrador
	 */
	public void generarClaveAdministrador() {

		Conexion conexion = new Conexion();

		try {
			boolean ok = true;
			if (this.administradorTransaccion != null && this.administradorTransaccion.gettTipoClave() != null && this.administradorTransaccion.gettTipoClave().equals("A")) {

				this.administradorTransaccion.setClave(this.getClaveAleatoria());

			} else {

				if (this.isVacio(this.administradorTransaccion.getClave())) {
					ok = false;
				}

			}
			if (ok) {
				conexion.setAutoCommitBD(false);

				this.administradorTransaccion.getCamposBD();

				conexion.actualizarBD(this.administradorTransaccion.getEstructuraTabla().getTabla(), this.administradorTransaccion.getEstructuraTabla().getPersistencia(), this.administradorTransaccion.getEstructuraTabla().getLlavePrimaria(), null);
				conexion.commitBD();

				this.mostrarMensajeGlobalPersonalizado(this.getMensaje("claveAleatoria", this.administradorTransaccion.getClave()), "exito");

				IEmail.enviarCorreo(this.getMensaje("mensajeClaveAleatoria", this.administradorTransaccion.getNombres(), this.administradorTransaccion.getClave()), this.getMensaje("asuntoClaveAleatoria"), this.administradorTransaccion.getCorreoElectronico());
				this.mostrarMensajeGlobalPersonalizado(this.getMensaje("claveCorreoExitoso", this.administradorTransaccion.getCorreoElectronico()), "exito");

				this.cerrarModal("panelClaveAdministrador");

				// reseteo de variables
				this.administradorTransaccion = null;
				this.getAdministradorTransaccion();
				this.administradores = null;
				this.getAdministradores();

			} else {

				this.mostrarMensajeGlobal("claveEnBlanco", "error");
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Edita un registro de administrador de software
	 */
	public void editarAdministrador() {
		Conexion conexion = new Conexion();

		try {
			if (isValidoAdministrador("E")) {
				conexion.setAutoCommitBD(false);

				this.administradorTransaccion.setNombres(this.getSinEspacios(this.administradorTransaccion.getNombres()));

				this.administradorTransaccion.getCamposBD();
				conexion.actualizarBD(this.administradorTransaccion.getEstructuraTabla().getTabla(), this.administradorTransaccion.getEstructuraTabla().getPersistencia(), this.administradorTransaccion.getEstructuraTabla().getLlavePrimaria(), null);
				conexion.commitBD();
				this.mostrarMensajeGlobal("edicionExitosa", "exito");
				this.mostrarMensajeGlobal("algunosCambios", "advertencia");
				this.cerrarModal("panelEdicionAdministrador");

				// reseteo de variables
				this.administradorTransaccion = null;
				this.getAdministradorTransaccion();
				this.administradores = null;
				this.getAdministradores();
			}

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
		} finally {
			conexion.cerrarConexion();
		}

	}

	/**
	 * Elimina un registro de adminiistador
	 */
	public void eliminarAdministrador() {

		Conexion conexion = new Conexion();
		try {

			conexion.setAutoCommitBD(false);
			this.administradorTransaccion.getCamposBD();
			conexion.eliminarBD(this.administradorTransaccion.getEstructuraTabla().getTabla(), this.administradorTransaccion.getEstructuraTabla().getLlavePrimaria());
			conexion.commitBD();
			this.mostrarMensajeGlobal("eliminacionExitosa", "exito");

		} catch (Exception e) {
			conexion.rollbackBD();
			this.mostrarMensajeGlobal("transaccionFallida", "error");
			this.mostrarMensajeGlobal("eliminacionFallida", "error");

		} finally {
			conexion.cerrarConexion();
		}

		// reseteo de variables
		this.administradorTransaccion = null;
		this.administradores = null;
		this.getAdministradores();

	}

	/**
	 * Este método borra el formulario de creación de un administrador
	 */
	public void cancelarAdministrador() {

		try {
			this.administrador = new Administrador();

			this.administradores = null;
			this.getAdministradores();
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	/**
	 * Este método borra el formulario de edición de un administrador en
	 * transacción
	 */
	public void cancelarAdministradorTransaccion(String aVista) {
		try {

			this.administradorTransaccion = new Administrador();
			this.administradores = null;
			this.getAdministradores();

			if (aVista != null && aVista.equals("MODAL_EDITAR_ADMINISTRADOR")) {
				this.cerrarModal("panelEdicionAdministrador");

			} else if (aVista != null && aVista.equals("MODAL_CLAVE_ADMINISTRADOR")) {
				this.cerrarModal("panelClaveAdministrador");

			} else if (aVista != null && aVista.equals("MODAL_ELIMINAR_ADMINISTRADOR")) {
				this.cerrarModal("panelEliminacionAdministrador");

			}

		} catch (Exception e) {

			IConstantes.log.error(e, e);

		}

	}

	/**
	 * Asigna un administrador para realizar una acción
	 * 
	 * @param aAdministrador
	 * @param aVista
	 */
	public void asignarAdministrador(Administrador aAdministrador, String aVista) {

		try {

			this.administradorTransaccion = aAdministrador;

			if (aVista != null && aVista.equals("MODAL_EDITAR_ADMINISTRADOR")) {
				this.abrirModal("panelEdicionAdministrador");

			} else if (aVista != null && aVista.equals("MODAL_CLAVE_ADMINISTRADOR")) {
				this.abrirModal("panelClaveAdministrador");

			} else {

				this.abrirModal("panelEliminacionAdministrador");

			}

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

	}

	public List<Administrador> getAdministradores() {
		try {
			if (this.administradores == null) {

				this.administradores = IConsultasDAO.getAdministradores(null);
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	// get/sets

	public Administrador getAdministrador() {
		try {
			if (this.administrador == null) {
				this.administrador = new Administrador();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Administrador getAdministradorTransaccion() {
		try {
			if (this.administradorTransaccion == null) {
				this.administradorTransaccion = new Administrador();

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return administradorTransaccion;
	}

	public void setAdministradorTransaccion(Administrador administradorTransaccion) {
		this.administradorTransaccion = administradorTransaccion;
	}

	public List<SelectItem> getItemsAreas() {
		try {
			if (this.itemsAreas == null) {
				this.itemsAreas = new ArrayList<SelectItem>();

				List<Area> areas = IConsultasDAO.getAreas(null);
				this.itemsAreas.add(new SelectItem("", this.getMensaje("comboVacio")));
				if (areas != null && areas.size() > 0) {
					areas.forEach(p -> this.itemsAreas.add(new SelectItem(p.getId(), p.getNombre())));
				}

			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return itemsAreas;
	}

	public void setItemsAreas(List<SelectItem> itemsAreas) {
		this.itemsAreas = itemsAreas;
	}

}
