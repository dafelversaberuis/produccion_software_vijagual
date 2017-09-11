
<%@page import="web.modulos.contenido.AdministrarContenido"%>
<%@page import="web.beans.Buscador"%>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.sql.*,java.util.*,java.text.SimpleDateFormat" errorPage=""
	session="false"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String texto = request.getParameter("texto");
	String idioma = request.getParameter("idioma");
	AdministrarContenido consultarInformacion = new AdministrarContenido();
	List<Buscador> resultados = consultarInformacion.getResultados(texto, idioma);  

	if (resultados!=null && resultados.size() > 0) {
%>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td bgcolor="#EE3135">
			<div align="center" style="color: #FFFFFF; font-size:14px ">#</div>
		</td>
		<td bgcolor="#EE3135">
			<div align="center" style="color: #FFFFFF; font-size:14px"><%=idioma.equals("es") ? "Resultado" : "Result"%></div>
		</td>
		<td bgcolor="#EE3135">
			<div align="center" style="color: #FFFFFF; font-size:14px"></div>
		</td>
	</tr>
	<%
		int j = 0;
			for (Buscador i : resultados) {
				j++;
	%>
	<tr onMouseOver="style.backgroundColor='#CCD9E6'" style="cursor: pointer;"
		onMouseOut="style.backgroundColor='#EEEEEE'"
		 bgcolor="#EEEEEE"  onclick="window.document.location='<%=i.getUrl()%>';">  
		<td align="center" style="font-size:14px"><font color="black"><%=j%></font></td>
		<td align="center">
			<div align="left" style="font-size:14px">
				<font color="black"><%=i.getTipo()+" "+i.getTituloDondeEncuentra()%></font>
			</div>
		</td>

		<td align="center"><a href="<%=i.getUrl()%>" style="font-size:14px; font-weight:bold"><%=idioma.equals("es") ? "IR" : "GO"%></a></td>

	</tr>
	<%
		}
	%>
</table>
<br />

<%
	} else {
%>
<%=idioma.equals("es") ? "No existen resultados disponibles para la frase escrita" : "No results available for the written sentence"%>
<%
	}
%>




