package web.beans;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import web.generales.IConstantes;

public class Administrador implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2176835921808996615L;
	private Integer						id;
	private String						nombres;
	private String						correoElectronico;
	private String						estadoVigencia;
	private String						clave;
	private String						tTipoClave;

	private boolean						recibirHojasVida;
	private boolean						cambiarContenido;
	private boolean						recibirPlanillaje;

	private Area							area;

	private EstructuraTabla		estructuraTabla;

	public Administrador() {
		this.estructuraTabla = new EstructuraTabla();
		this.area = new Area();

	}

	public void getCamposBD() {

		this.estructuraTabla.setTabla("administradores");
		this.estructuraTabla.getLlavePrimaria().put("id", this.id);

		this.estructuraTabla.getPersistencia().put("nombres", this.nombres);
		this.estructuraTabla.getPersistencia().put("correo_electronico", this.correoElectronico);
		this.estructuraTabla.getPersistencia().put("estado_vigencia", this.estadoVigencia);
		this.estructuraTabla.getPersistencia().put("clave", this.clave);

		this.estructuraTabla.getPersistencia().put("recibir_hv", this.recibirHojasVida);
		this.estructuraTabla.getPersistencia().put("cambiar_contenido", this.cambiarContenido);
		this.estructuraTabla.getPersistencia().put("recibir_planillaje", this.recibirPlanillaje);

		this.estructuraTabla.getPersistencia().put("id_area", this.area.getId());

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Size(max = 250, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = IConstantes.VALIDACION_EMAIL_INCORRECTO)
	@Size(max = 250, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Pattern(regexp = "[AI]", message = IConstantes.VALIDACION_ACTIVO_INACTIVO)
	public String getEstadoVigencia() {
		return estadoVigencia;
	}

	public void setEstadoVigencia(String estadoVigencia) {
		this.estadoVigencia = estadoVigencia;
	}

	@Size(max = 250, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public EstructuraTabla getEstructuraTabla() {
		return estructuraTabla;
	}

	public void setEstructuraTabla(EstructuraTabla estructuraTabla) {
		this.estructuraTabla = estructuraTabla;
	}

	public String gettTipoClave() {
		return tTipoClave;
	}

	public void settTipoClave(String tTipoClave) {
		this.tTipoClave = tTipoClave;
	}

	public boolean isRecibirHojasVida() {
		return recibirHojasVida;
	}

	public void setRecibirHojasVida(boolean recibirHojasVida) {
		this.recibirHojasVida = recibirHojasVida;
	}

	public boolean isCambiarContenido() {
		return cambiarContenido;
	}

	public void setCambiarContenido(boolean cambiarContenido) {
		this.cambiarContenido = cambiarContenido;
	}

	public boolean isRecibirPlanillaje() {
		return recibirPlanillaje;
	}

	public void setRecibirPlanillaje(boolean recibirPlanillaje) {
		this.recibirPlanillaje = recibirPlanillaje;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
