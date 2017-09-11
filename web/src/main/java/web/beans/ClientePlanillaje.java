package web.beans;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;

public class ClientePlanillaje implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6832400169717629776L;
	private String						telefonos;
	private String						correo;
	private String						nombres;
	private Long							cedula;

	private EstructuraTabla		estructuraTabla;

	public void getCamposBD() {

		this.estructuraTabla.setTabla("clientes_planillaje");
		this.estructuraTabla.getLlavePrimaria().put("cedula", this.cedula);
		this.estructuraTabla.getPersistencia().put("nombres", this.nombres);
		this.estructuraTabla.getPersistencia().put("correo", this.correo);
		this.estructuraTabla.getPersistencia().put("telefonos", this.telefonos);

	}

	public ClientePlanillaje() {
		this.estructuraTabla = new EstructuraTabla();
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public EstructuraTabla getEstructuraTabla() {
		return estructuraTabla;
	}

	public void setEstructuraTabla(EstructuraTabla estructuraTabla) {
		this.estructuraTabla = estructuraTabla;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	@Email(message = "Formato incorrecto")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
