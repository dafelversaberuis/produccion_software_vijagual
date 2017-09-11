package web.modulos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web.Conexion;
import web.beans.Administrador;
import web.beans.Area;
import web.beans.Baner;
import web.beans.Buscador;
import web.beans.Menu;
import web.beans.Seccion;
import web.beans.SubMenu;

public interface IConsultasDAO {

	/**
	 * Obtiene un listado de las fotos de abners
	 * 
	 * @param aFoto
	 * @return fotos
	 * @throws Exception
	 */
	public static List<Baner> getFotosBaners(Baner aFoto) throws Exception {
		List<Baner> fotos = new ArrayList<Baner>();
		List<Object> prametros = new ArrayList<Object>();
		Baner foto = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT p.leyenda, p.id, foto_decodificada, archivo");
			sql.append("  FROM baners p");
			sql.append("  WHERE 1=1 ");

			sql.append("  ORDER BY leyenda");

			rs = conexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				foto = new Baner();
				foto.setId((Integer) rs.getObject("id"));
				foto.setLeyenda((String) rs.getObject("leyenda"));
				foto.settFotoDecodificada((String) rs.getObject("foto_decodificada"));
				// foto.setArchivo(rs.getBytes("archivo"));
				fotos.add(foto);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			conexion.cerrarConexion();

		}
		return fotos;

	}

	/**
	 * Obtiene la sección/secciones a identificar
	 * 
	 * @param aSeccion
	 * @return secciones
	 * @throws Exception
	 */
	public static List<Seccion> getSecciones(Seccion aSeccion) throws Exception {
		List<Seccion> secciones = new ArrayList<Seccion>();
		List<Object> prametros = new ArrayList<Object>();
		Seccion seccion = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT *");
			sql.append("  FROM secciones_pagina_principal p");
			sql.append("  WHERE 1=1 ");

			if (aSeccion != null && aSeccion.getId() != null) {
				sql.append("  AND p.id = ? ");
				prametros.add(aSeccion.getId());

			}

			sql.append("  ORDER BY id");

			rs = conexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				seccion = new Seccion();
				seccion.setId((Integer) rs.getObject("id"));
				seccion.setNombreIdentificacion((String) rs.getObject("nombre_identificacion"));
				seccion.setContenido((String) rs.getObject("contenido"));
				seccion.setContenidoIngles((String) rs.getObject("contenido_ingles"));

				secciones.add(seccion);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			conexion.cerrarConexion();

		}
		return secciones;

	}

	/**
	 * Submenus con conexion como parámetro
	 * 
	 * @param aSubMenu
	 * @return submenus
	 * @throws Exception
	 */
	public static List<SubMenu> getSubmenus(Conexion aConexion, SubMenu aSubMenu) throws Exception {
		List<SubMenu> submenus = new ArrayList<SubMenu>();
		List<Object> prametros = new ArrayList<Object>();
		SubMenu submenu = null;

		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT *");
			sql.append("  FROM submenus p");
			sql.append("  WHERE 1=1 ");

			if (aSubMenu != null && aSubMenu.getMenu() != null && aSubMenu.getMenu().getId() != null) {
				sql.append("  AND p.id_menu =  ? ");
				prametros.add(aSubMenu.getMenu().getId());
			}

			sql.append("  ORDER BY posicion");

			rs = aConexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				submenu = new SubMenu();
				submenu.setId((Integer) rs.getObject("id"));
				submenu.setTitulo((String) rs.getObject("titulo"));
				submenu.setTituloIngles((String) rs.getObject("titulo_ingles"));
				submenu.setContenido((String) rs.getObject("contenido"));
				submenu.setContenidoIngles((String) rs.getObject("contenido_ingles"));
				submenu.setPosicion((Integer) rs.getObject("posicion"));
				submenu.setIndicativoVigencia((String) rs.getObject("indicativo_vigencia"));

				submenu.getMenu().setId((Integer) rs.getObject("id_menu"));

				submenus.add(submenu);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}

		}
		return submenus;

	}

	/**
	 * Obtiene los submenus
	 * 
	 * @param aSubMenu
	 * @return submenus
	 * @throws Exception
	 */
	public static List<SubMenu> getSubmenus(SubMenu aSubMenu) throws Exception {
		List<SubMenu> submenus = new ArrayList<SubMenu>();
		List<Object> prametros = new ArrayList<Object>();
		SubMenu submenu = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT *");
			sql.append("  FROM submenus p");
			sql.append("  WHERE 1=1 ");

			if (aSubMenu != null && aSubMenu.getId() != null) {
				sql.append("  AND p.id =  ? ");
				prametros.add(aSubMenu.getId());
			}

			if (aSubMenu != null && aSubMenu.getMenu() != null && aSubMenu.getMenu().getId() != null) {
				sql.append("  AND p.id_menu =  ? ");
				prametros.add(aSubMenu.getMenu().getId());
			}

			sql.append("  ORDER BY posicion");

			rs = conexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				submenu = new SubMenu();
				submenu.setId((Integer) rs.getObject("id"));
				submenu.setTitulo((String) rs.getObject("titulo"));
				submenu.setTituloIngles((String) rs.getObject("titulo_ingles"));
				submenu.setContenido((String) rs.getObject("contenido"));
				submenu.setContenidoIngles((String) rs.getObject("contenido_ingles"));
				submenu.setPosicion((Integer) rs.getObject("posicion"));
				submenu.setIndicativoVigencia((String) rs.getObject("indicativo_vigencia"));

				submenu.getMenu().setId((Integer) rs.getObject("id_menu"));

				submenus.add(submenu);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			conexion.cerrarConexion();

		}
		return submenus;

	}

	/**
	 * Obtiene un listado de meus consulta sobre transacción
	 * 
	 * @param aConexion
	 * @return menus
	 * @throws Exception
	 */
	public static List<Menu> getMenus(Conexion aConexion) throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		List<Object> prametros = new ArrayList<Object>();
		Menu menu = null;

		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT *");
			sql.append("  FROM menus p");
			sql.append("  WHERE 1=1 ");

			sql.append("  ORDER BY posicion");

			rs = aConexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				menu = new Menu();
				menu.setId((Integer) rs.getObject("id"));
				menu.setTitulo((String) rs.getObject("titulo"));
				menu.setTituloIngles((String) rs.getObject("titulo_ingles"));
				menu.setContenido((String) rs.getObject("contenido"));
				menu.setContenidoIngles((String) rs.getObject("contenido_ingles"));
				menu.setPoseeContenido((String) rs.getObject("posee_contenido"));
				menu.setPosicion((Integer) rs.getObject("posicion"));
				menu.setIndicativoVigencia((String) rs.getObject("indicativo_vigencia"));

				menus.add(menu);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}

		}
		return menus;

	}

	/**
	 * Obtiene lo smenus de la página web
	 * 
	 * @param aMenu
	 * @return menus
	 * @throws Exception
	 */
	public static List<Menu> getMenus(Menu aMenu) throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		List<Object> prametros = new ArrayList<Object>();
		Menu menu = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT *");
			sql.append("  FROM menus p");
			sql.append("  WHERE 1=1 ");

			if (aMenu != null && aMenu.getId() != null) {
				sql.append("  AND p.id = ? ");
				prametros.add(aMenu.getId());
			}

			if (aMenu != null && aMenu.getIndicativoVigencia() != null && !aMenu.getIndicativoVigencia().trim().equals("")) {

				sql.append("  AND p.indicativo_vigencia = ? ");
				prametros.add(aMenu.getIndicativoVigencia());

			}

			sql.append("  ORDER BY posicion ");

			rs = conexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				menu = new Menu();
				menu.setId((Integer) rs.getObject("id"));
				menu.setTitulo((String) rs.getObject("titulo"));
				menu.setTituloIngles((String) rs.getObject("titulo_ingles"));
				menu.setContenido((String) rs.getObject("contenido"));
				menu.setContenidoIngles((String) rs.getObject("contenido_ingles"));
				menu.setPoseeContenido((String) rs.getObject("posee_contenido"));
				menu.setPosicion((Integer) rs.getObject("posicion"));
				menu.setIndicativoVigencia((String) rs.getObject("indicativo_vigencia"));

				menus.add(menu);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			conexion.cerrarConexion();

		}
		return menus;

	}

	/**
	 * Obtiene un listado de administradores
	 * 
	 * @param aAdministrador
	 * @return administradores
	 * @throws Exception
	 */
	public static List<Administrador> getAdministradores(Administrador aAdministrador) throws Exception {
		List<Administrador> administradores = new ArrayList<Administrador>();
		List<Object> prametros = new ArrayList<Object>();
		Administrador administrador = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT p.*, a.nombre");
			sql.append("  FROM administradores p");
			sql.append("  LEFT JOIN areas a ON a.id = p.id_area");
			sql.append("  WHERE 1=1 ");

			if (aAdministrador != null && aAdministrador.getCorreoElectronico() != null && !aAdministrador.getCorreoElectronico().trim().equals("")) {
				sql.append("  AND UPPER(p.correo_electronico) = ?");
				prametros.add(aAdministrador.getCorreoElectronico().trim().toUpperCase());
			}

			if (aAdministrador != null && aAdministrador.getClave() != null && !aAdministrador.getClave().trim().equals("")) {
				sql.append("  AND p.clave = ?");
				prametros.add(aAdministrador.getClave().trim());
			}

			if (aAdministrador != null && aAdministrador.getEstadoVigencia() != null && !aAdministrador.getEstadoVigencia().trim().equals("")) {
				sql.append("  AND p.estado_vigencia = ?");
				prametros.add(aAdministrador.getEstadoVigencia().trim());
			}

			sql.append("  ORDER BY nombres");

			rs = conexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				administrador = new Administrador();
				administrador.setId((Integer) rs.getObject("id"));
				administrador.setNombres((String) rs.getObject("nombres"));
				administrador.setCorreoElectronico((String) rs.getObject("correo_electronico"));
				administrador.setClave((String) rs.getObject("clave"));
				administrador.setEstadoVigencia((String) rs.getObject("estado_vigencia"));
				administrador.setCambiarContenido((boolean) rs.getObject("cambiar_contenido"));
				administrador.setRecibirHojasVida((boolean) rs.getObject("recibir_hv"));
				administrador.setRecibirPlanillaje((boolean) rs.getObject("recibir_planillaje"));
				administrador.getArea().setId((Integer) rs.getObject("id_area"));
				administrador.getArea().setNombre((String) rs.getObject("nombre"));
				administradores.add(administrador);
			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			conexion.cerrarConexion();

		}
		return administradores;

	}

	/**
	 * Obtiene un listado de areas
	 * 
	 * @param aArea
	 * @return areas
	 * @throws Exception
	 */
	public static List<Area> getAreas(Area aArea) throws Exception {

		List<Area> areas = new ArrayList<Area>();
		List<Object> parametros = new ArrayList<Object>();
		Area area = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT a.*");
			sql.append("  FROM areas a ");
			sql.append("  WHERE 1=1");

			if (aArea != null && aArea.getId() != null) {
				sql.append("  AND a.id = ? ");
				parametros.add(aArea.getId());
			}

			sql.append("  ORDER BY a.nombre ");

			rs = conexion.consultarBD(sql.toString(), parametros);

			while (rs.next()) {

				area = new Area();

				area.setId((Integer) rs.getObject("id"));
				area.setNombre((String) rs.getObject("nombre"));

				areas.add(area);

			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			conexion.cerrarConexion();

		}

		return areas;

	}

	/**
	 * Encuentra lo adecuado
	 * 
	 * @param aTexto
	 * @param aIdioma
	 * @return encontrados
	 * @throws Exception
	 */
	public static List<Buscador> getBuscado(String aTexto, String aIdioma) throws Exception {
		List<Buscador> encontrados = new ArrayList<Buscador>();
		List<Object> prametros = new ArrayList<Object>();
		Buscador encontrado = null;
		Conexion conexion = new Conexion();
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {

			// buscamos en los submenus

			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT distinct p.titulo, p.titulo_ingles, p.id");
			sql.append("  FROM submenus p");
			sql.append("  WHERE 1=1 ");

			if (aIdioma != null && aIdioma.equals("es")) {

				sql.append("  AND (lower(p.titulo) LIKE  ?  OR lower(p.contenido) LIKE  ? )");

			} else {

				sql.append("  AND (lower(p.titulo_ingles) LIKE  ?  OR lower(p.contenido_ingles) LIKE  ? )");

			}

			prametros.add("%" + aTexto.trim().toLowerCase() + "%");
			prametros.add("%" + aTexto.trim().toLowerCase() + "%");

			sql.append("  ORDER BY p.titulo, p.titulo_ingles");

			rs = conexion.consultarBD(sql.toString(), prametros);

			while (rs.next()) {

				encontrado = new Buscador();

				if (aIdioma != null && aIdioma.equals("es")) {
					encontrado.setTipo("Se encuentra en el Menú");
					encontrado.setTituloDondeEncuentra("<b>" + (String) rs.getObject("titulo") + "</b>");
					encontrado.setUrl("submenu.jsp?id=" + (Integer) rs.getObject("id") + "&i=" + aIdioma);

				} else {

					encontrado.setTipo("It is in the Menu");
					encontrado.setTituloDondeEncuentra("<b>" + (String) rs.getObject("titulo_ingles") + "</b>");
					encontrado.setUrl("submenu.jsp?id=" + (Integer) rs.getObject("id") + "&i=" + aIdioma);

				}

				encontrados.add(encontrado);
			}

			// buscamos en la página principal
			prametros = new ArrayList<Object>();
			sql = new StringBuilder();
			sql.append("  SELECT distinct p.id");
			sql.append("  FROM secciones_pagina_principal p");
			sql.append("  WHERE 1=1 ");

			if (aIdioma != null && aIdioma.equals("es")) {

				sql.append("  AND lower(p.contenido) LIKE  ? ");

			} else {

				sql.append("  AND lower(p.contenido_ingles) LIKE  ? ");

			}

			prametros.add("%" + aTexto.trim().toLowerCase() + "%");

			rs2 = conexion.consultarBD(sql.toString(), prametros);

			if (rs2.next()) {

				encontrado = new Buscador();

				if (aIdioma != null && aIdioma.equals("es")) {
					encontrado.setTipo("Se encuentra en ");
					encontrado.setTituloDondeEncuentra("<b>esta página principal</b>");
					encontrado.setUrl("index.jsp?id=" + (Integer) rs2.getObject("id") + "&i=" + aIdioma);

				} else {

					encontrado.setTipo("It is in ");
					encontrado.setTituloDondeEncuentra("<b>this main page</b>");
					encontrado.setUrl("index.jsp?id=" + (Integer) rs2.getObject("id") + "&i=" + aIdioma);

				}

				encontrados.add(encontrado);

			}

			// si busca las herraminetas online

			String textoOk = aTexto.trim().toLowerCase();

			if (aIdioma != null && aIdioma.equals("es")) {
				encontrado = new Buscador();
				// planillaje
				if (textoOk.contains("planillaje") || textoOk.contains("planilla") || textoOk.contains("planillaje en linea")) {

					encontrado.setTipo("Se encuentra en la herramienta online");
					encontrado.setTituloDondeEncuentra("<b>planillaje</b> (Menú USUARIOS)");
					encontrado.setUrl("planillaje.jsf?i=" + aIdioma);
					encontrados.add(encontrado);

				}

				// contáctenos
				else if (textoOk.contains("contáctenos") || textoOk.contains("contactenos") || textoOk.contains("contacto") || textoOk.contains("escribenos")) {
					encontrado.setTipo("Se encuentra en la herramienta online");
					encontrado.setTituloDondeEncuentra("<b>contáctenos</b> (Menú USUARIOS)");
					encontrado.setUrl("contactenos.jsf?i=" + aIdioma);
					encontrados.add(encontrado);
				}

				// pqr
				else if (textoOk.contains("pqr") || textoOk.contains("pqrs") || textoOk.contains("peticion") || textoOk.contains("petición") || textoOk.contains("queja") || textoOk.contains("reclamo") || textoOk.contains("felicitacion") || textoOk.contains("solicitud")) {
					encontrado.setTipo("Se encuentra en la herramienta online");
					encontrado.setTituloDondeEncuentra("<b>PQR</b> (Menú USUARIOS)");
					encontrado.setUrl("pqrs.jsf?i=" + aIdioma);
					encontrados.add(encontrado);
				}

				// hoja de vida

				else if (textoOk.contains("hoja de vida") || textoOk.contains("empleo") || textoOk.contains("pasantias") || textoOk.contains("trabajar") || textoOk.contains("trabajo") || textoOk.contains("talento") || textoOk.contains("profesionales")) {
					encontrado.setTipo("Se encuentra en la herramienta online");
					encontrado.setTituloDondeEncuentra("<b>Hoja de Vida</b> (Menú USUARIOS)");
					encontrado.setUrl("hojaVida.jsf?i=" + aIdioma);
					encontrados.add(encontrado);
				}

			} else {
				encontrado = new Buscador();
				// planillaje
				if (textoOk.contains("planing") || textoOk.contains("payroll") || textoOk.contains("online scheduling")) {

					encontrado.setTipo("It is in the online tool");
					encontrado.setTituloDondeEncuentra("<b>Planing</b> (USERS Menu)");
					encontrado.setUrl("planillaje.jsf?i=" + aIdioma);
					encontrados.add(encontrado);

				}

				// contáctenos
				else if (textoOk.contains("contact us") || textoOk.contains("contact") || textoOk.contains("write")) {
					encontrado.setTipo("It is in the online tool");
					encontrado.setTituloDondeEncuentra("<b>Contact Us</b> (USERS Menu)");
					encontrado.setUrl("contactenos.jsf?i=" + aIdioma);
					encontrados.add(encontrado);
				}

				// pqr
				else if (textoOk.contains("pqr") || textoOk.contains("pqrs") || textoOk.contains("petition") || textoOk.contains("complain") || textoOk.contains("congratulation") || textoOk.contains("request")) {
					encontrado.setTipo("It is in the online tool");
					encontrado.setTituloDondeEncuentra("<b>PQR</b> (USERS Menu)");
					encontrado.setUrl("pqrs.jsf?i=" + aIdioma);
					encontrados.add(encontrado);
				}

				// hoja de vida

				else if (textoOk.contains("curriculum") || textoOk.contains("vitae") || textoOk.contains("pastimes") || textoOk.contains("work") || textoOk.contains("cv") || textoOk.contains("talent") || textoOk.contains("professional")) {
					encontrado.setTipo("It is in the online tool");
					encontrado.setTituloDondeEncuentra("<b>Curriculum Vitae</b> (USERS Menu)");
					encontrado.setUrl("hojaVida.jsf?i=" + aIdioma);
					encontrados.add(encontrado);
				}

			}

		} catch (Exception e) {
			throw new Exception(e);

		} finally {

			if (rs != null) {
				rs.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			conexion.cerrarConexion();

		}
		return encontrados;

	}

}
