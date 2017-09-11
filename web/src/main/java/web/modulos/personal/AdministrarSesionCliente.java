package web.modulos.personal;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import web.beans.Administrador;
import web.beans.Personal;
import web.generales.ConsultarFuncionesAPI;
import web.generales.IConstantes;
import web.modulos.IConsultasDAO;

@ManagedBean
@SessionScoped
public class AdministrarSesionCliente extends ConsultarFuncionesAPI implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8662958392026228041L;
	private Personal					personalSesion;
	private Personal					personal;

	public void init() {

	}

	// privados

	/**
	 * Obtiene validación de rol no admitido
	 * 
	 * @return pagina
	 */
	public String getNoRolAdmitido(String aInterfaz) {
		String pagina = null;
		int sw = 2;

		if (this.personalSesion != null && this.personalSesion.getId() != null) {
			sw = 0;

		}

		if (sw == 2) {
			pagina = IConstantes.PAGINA_NO_LOGUEO;

			this.mostrarMensajeGlobal("noPoseePrivilegiosSobreInterfaz", "advertencia");
			// Guarda el mensaje antes de redireccionar y lo muestra
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);

		}

		return pagina;
	}

	/**
	 * Determina si un personal esta en sesión y lo deja o no acceder
	 * 
	 * @return pagina
	 */
	public String getNologueoPersonal() {
		String pagina = null;
		if (!(this.personalSesion != null && this.personalSesion.getId() != null)) {

			pagina = IConstantes.PAGINA_NO_LOGUEO;

			this.mostrarMensajeGlobal("noPoseePrivilegios", "advertencia");
			// Guarda el mensaje antes de redireccionar y lo muestra
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);

		}

		return pagina;
	}

	// publicos

	/**
	 * Cierra la sesión del administrador
	 */
	public String getCerrarSesion() {
		String pagina = IConstantes.PAGINA_NO_LOGUEO;
		this.personalSesion = null;

		// this.vistaLogueado = 0;

		this.mostrarMensajeGlobal("cierreSesionCorrecto", "exito");

		// Guarda el mensaje antes de redireccionar y lo muestra
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);

		return pagina;

	}

	/**
	 * Permite el acceso del personal
	 */
	public String accederPersonal() {
		String pagina = null;

		List<Administrador> administradores = null;

		try {

			if (this.personal != null && this.personal.getCorreoElectronico() != null && !this.personal.getCorreoElectronico().trim().equals("") && this.personal.getClave() != null && !this.personal.getClave().trim().equals("")) {

				Administrador admin = new Administrador();
				admin.setClave(this.personal.getClave());
				admin.setCorreoElectronico(this.personal.getCorreoElectronico().trim());
				admin.setEstadoVigencia(IConstantes.ACTIVO);
				administradores = IConsultasDAO.getAdministradores(admin);

				if (administradores != null && administradores.size() > 0 && administradores.get(0) != null && administradores.get(0).getId() != null) {

					this.personalSesion = new Personal();
					this.personalSesion.setCorreoElectronico(administradores.get(0).getCorreoElectronico().trim());
					this.personalSesion.setNombreCompleto(administradores.get(0).getNombres().trim());
					this.personalSesion.setId(administradores.get(0).getId());

					this.mostrarMensajeGlobal("ingresoCorrecto", "exito");
					this.personal = null;

					pagina = IConstantes.PAGINA_HOME;

					// Guarda el mensaje antes de redireccionar y lo muestra
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getFlash().setKeepMessages(true);

				} else {
					this.mostrarMensajeGlobal("noCoincideCredenciales", "advertencia");
				}

			} else {

				this.mostrarMensajeGlobal("noCoincideCredenciales", "advertencia");

			}

		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}

		return pagina;
	}

	/**
	 * Obtiene los datos de sesion de un personal
	 * 
	 * @return personalSesion
	 */
	public Personal getPersonalSesion() {
		try {
			if (this.personalSesion == null) {
				this.personalSesion = new Personal();
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return personalSesion;
	}

	/**
	 * Establece los datos de sesión de un personal
	 * 
	 * @param personalSesion
	 */
	public void setPersonalSesion(Personal personalSesion) {
		this.personalSesion = personalSesion;
	}

	/**
	 * Obtiene el personal que se logeua
	 * 
	 * @return personal
	 */
	public Personal getPersonal() {
		try {
			if (this.personal == null) {
				this.personal = new Personal();
			}
		} catch (Exception e) {
			IConstantes.log.error(e, e);
		}
		return personal;
	}

	/**
	 * Establece el personal que se loguea
	 * 
	 * @param personal
	 */
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

}
