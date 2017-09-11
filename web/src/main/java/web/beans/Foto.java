package web.beans;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.primefaces.model.UploadedFile;

import web.generales.IConstantes;

public class Foto implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4245707882495550975L;
	private Integer						id;
	private String						leyenda;
	private byte[]						archivo;
	private String						tFotoDecodificada;
	private String						tNombreFoto;

	private UploadedFile			tFile;

	private EstructuraTabla		estructuraTabla;

	public Foto() {
		this.estructuraTabla = new EstructuraTabla();

	}

	public void getCamposBD() {

		this.estructuraTabla.setTabla("fotos");
		this.estructuraTabla.getLlavePrimaria().put("id", this.id);
		this.estructuraTabla.getPersistencia().put("leyenda", this.leyenda);
		this.estructuraTabla.getPersistencia().put("archivo", this.archivo);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Size(max = 100, message = IConstantes.VALIDACION_MAXIMA_LONGITUD)
	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String gettFotoDecodificada() {
		return tFotoDecodificada;
	}

	public void settFotoDecodificada(String tFotoDecodificada) {
		this.tFotoDecodificada = tFotoDecodificada;
	}

	public String gettNombreFoto() {
		return tNombreFoto;
	}

	public void settNombreFoto(String tNombreFoto) {
		this.tNombreFoto = tNombreFoto;
	}

	public UploadedFile gettFile() {
		return tFile;
	}

	public void settFile(UploadedFile tFile) {
		this.tFile = tFile;
	}

	public EstructuraTabla getEstructuraTabla() {
		return estructuraTabla;
	}

	public void setEstructuraTabla(EstructuraTabla estructuraTabla) {
		this.estructuraTabla = estructuraTabla;
	}

}
