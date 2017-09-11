package web.beans;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class Planillaje implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4837043091769552099L;
	private String						fecha;
	private String						fechaArchivo;

	private ClientePlanillaje	clientePlanillaje;

	private String						puestoCarne1;
	private String						puestoCarne2;
	private String						puestoViscera;
	private String						destinoPiel;

	private String						corralActual;
	private String						especie;
	private String						piel;
	private String						animal;
	private String						sexo;
	private String						pesoVivo;

	private String						tPoseeAdjunto;
	private byte[]						archivo;
	private String						contentType;
	private String						extension;
	private UploadedFile			tFile;

	private EstructuraTabla		estructuraTabla;

	public void getCamposBD() {

		this.estructuraTabla.setTabla("planillaje");
		this.estructuraTabla.getLlavePrimaria().put("fecha", this.fecha);

		this.estructuraTabla.getPersistencia().put("animal", this.animal);
		this.estructuraTabla.getPersistencia().put("sexo", this.sexo);
		this.estructuraTabla.getPersistencia().put("especie", this.especie);
		this.estructuraTabla.getPersistencia().put("peso_vivo", this.pesoVivo);
		this.estructuraTabla.getPersistencia().put("corral_actual", this.corralActual);

		this.estructuraTabla.getPersistencia().put("puesto_carne1", this.puestoCarne1);
		this.estructuraTabla.getPersistencia().put("puesto_carne2", this.puestoCarne2);
		this.estructuraTabla.getPersistencia().put("puesto_viscera", this.puestoViscera);
		this.estructuraTabla.getPersistencia().put("piel", this.piel);

		this.estructuraTabla.getPersistencia().put("archivo", this.archivo);
		this.estructuraTabla.getPersistencia().put("content_type", this.contentType);
		this.estructuraTabla.getPersistencia().put("extension", this.extension);

		this.estructuraTabla.getPersistencia().put("id_cedula", this.clientePlanillaje.getCedula());

	}

	public Planillaje() {
		this.clientePlanillaje = new ClientePlanillaje();
		this.estructuraTabla = new EstructuraTabla();
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

	public String getPuestoCarne1() {
		return puestoCarne1;
	}

	public void setPuestoCarne1(String puestoCarne1) {
		this.puestoCarne1 = puestoCarne1;
	}

	public String getPuestoCarne2() {
		return puestoCarne2;
	}

	public void setPuestoCarne2(String puestoCarne2) {
		this.puestoCarne2 = puestoCarne2;
	}

	public String getPuestoViscera() {
		return puestoViscera;
	}

	public void setPuestoViscera(String puestoViscera) {
		this.puestoViscera = puestoViscera;
	}

	public String getCorralActual() {
		return corralActual;
	}

	public void setCorralActual(String corralActual) {
		this.corralActual = corralActual;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getPiel() {
		return piel;
	}

	public void setPiel(String piel) {
		this.piel = piel;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPesoVivo() {
		return pesoVivo;
	}

	public void setPesoVivo(String pesoVivo) {
		this.pesoVivo = pesoVivo;
	}

	public EstructuraTabla getEstructuraTabla() {
		return estructuraTabla;
	}

	public void setEstructuraTabla(EstructuraTabla estructuraTabla) {
		this.estructuraTabla = estructuraTabla;
	}

	public ClientePlanillaje getClientePlanillaje() {
		return clientePlanillaje;
	}

	public void setClientePlanillaje(ClientePlanillaje clientePlanillaje) {
		this.clientePlanillaje = clientePlanillaje;
	}

	public String getDestinoPiel() {
		return destinoPiel;
	}

	public void setDestinoPiel(String destinoPiel) {
		this.destinoPiel = destinoPiel;
	}

}
