package web.beans;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.primefaces.model.UploadedFile;

public class HojaVida implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 993790466663563514L;
	private String						fecha;
	private String						fechaArchivo;

	private String						nombres;
	private Long							cedula;

	private String						telefonos;
	private String						ciudad;
	private String						correo;
	private String						descripcion;

	private String						tPoseeAdjunto;
	private byte[]						archivo;
	private String						contentType;
	private String						extension;
	private UploadedFile			tFile;

	public HojaVida() {

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

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Email(message = "Formato incorrecto")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String gettPoseeAdjunto() {
		return tPoseeAdjunto;
	}

	public void settPoseeAdjunto(String tPoseeAdjunto) {
		this.tPoseeAdjunto = tPoseeAdjunto;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public UploadedFile gettFile() {
		return tFile;
	}

	public void settFile(UploadedFile tFile) {
		this.tFile = tFile;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFechaArchivo() {
		return fechaArchivo;
	}

	public void setFechaArchivo(String fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}

}
