<%@page import="web.beans.Baner"%>
<%@page import="web.beans.SubMenu"%>
<%@page import="web.beans.Menu"%>
<%@page import="web.modulos.contenido.ConsultarInformacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ES" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es" data-ng-app="website">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<head>


<meta charset="utf-8">
<title>FRIGORÍFICO VIJAGUAL</title>
<link rel="SHORTCUT ICON"
	href="/web/mt-demo/55800/55854/mt-content/uploads/2015/11/favicon5d58.ico?_build=1483642918"
	type="image/vnd.microsoft.icon" />


<meta name="keywords" content="FRIGORÍFICO VIJAGUAL" />
<meta name="robots" content="FRIGORÍFICO VIJAGUAL" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="/web/mt-includes/css/assets.min3788.css?_build=1483642756" />
<link rel="stylesheet"
	href="/web/mt-demo/55800/55854/mt-content/themes/mt-0186/css/styles5d58.css?_build=1483642918" />
<style>
@import
	url(http://fonts.googleapis.com/css?family=Istok+Web:regular,italic,700,700italic|Krona+One:regular|Lato:100,100italic,300,300italic,regular,italic,700,700italic,900,900italic|Merriweather:300,300italic,regular,italic,700,700italic,900,900italic|Open+Sans:300,300italic,regular,italic,600,600italic,700,700italic,800,800italic&amp;subset=cyrillic,latin,latin-ext,cyrillic-ext,vietnamese,devanagari,greek-ext,greek)
	;

@import
	url(http://fonts.googleapis.com/css?family=Oswald:300,regular,700|PT+Sans:regular,italic,700,700italic|Raleway:100,200,300,regular,500,600,700,800,900|Roboto:100,100italic,300,300italic,regular,italic,500,500italic,700,700italic,900,900italic|Roboto+Condensed:300,300italic,regular,italic,700,700italic&amp;subset=cyrillic,latin,latin-ext,cyrillic-ext,vietnamese,devanagari,greek-ext,greek)
	;

@import
	url(http://fonts.googleapis.com/css?family=Russo+One:regular|Satisfy:regular|Six+Caps:regular|Sonsie+One:regular|Ubuntu:300,300italic,regular,italic,500,500italic,700,700italic&amp;subset=cyrillic,latin,latin-ext,cyrillic-ext,vietnamese,devanagari,greek-ext,greek)
	;
</style>
<link rel="stylesheet"
	href="/web/mt-demo/55800/55854/mt-content/assets/styles897b.css?_build=1481800664"
	id="moto-website-style" />








</head>
<body class="moto-background">




	<div class="page">

		<%
			//Recuperación de varaibles
			String idioma = request.getParameter("i");
			if (idioma == null || idioma.equals("es")) {
				idioma = "es";
			} else {
				idioma = "en";
			}
		%>

		<header id="section-header" class="header moto-section"
			data-widget="section" data-container="section">
		<div moto-sticky="{ }"
			class="moto-widget moto-widget-container moto-container_header_546f07b9"
			data-widget="container" data-container="container"
			data-css-name="moto-container_header_546f07b9">
			<div class="moto-widget moto-widget-row" data-widget="row">
				<div class="container-fluid">
					<div class="row">
						<div class="moto-cell col-sm-4" data-container="container">
							<div
								class="moto-widget moto-widget-spacer moto-preset-default               moto-spacing-top-small moto-spacing-right-auto moto-spacing-bottom-auto moto-spacing-left-auto"
								data-widget="spacer" data-preset="default" data-spacing="saaa">
								<div class="moto-widget-spacer-block" style="height: 1px;"></div>
							</div>
							<div data-grid-type="xs" class="moto-widget moto-widget-row"
								data-widget="row">
								<div class="container-fluid">
									<div class="row">
										<div class="moto-cell col-xs-3" data-container="container">
											<div data-widget-id="wid__image__586eaede4687b"
												class="moto-widget moto-widget-image moto-preset-default moto-align-right moto-spacing-top-small moto-spacing-right-auto moto-spacing-bottom-small moto-spacing-left-auto "
												data-preset="default" data-widget="image">
												<a class="moto-widget-image-link moto-link"
													href="index.jsp?i=<%=idioma%>" data-action="page"> <img
													src="/web/mt-demo/55800/55854/mt-content/uploads/2015/11/1.png">
												</a>
											</div>
										</div>
										<div class="moto-cell col-xs-9" data-container="container">
											<div
												class="moto-widget moto-widget-text moto-preset-default                     moto-spacing-top-small moto-spacing-right-auto moto-spacing-bottom-auto moto-spacing-left-auto"
												data-widget="text" data-preset="default" data-spacing="saaa">
												<div
													class="moto-widget-text-content moto-widget-text-editable">
													<p class="moto-text_system_1">
														<a class="moto-link" data-action="pages" data-id="1"
															href="index.jsp?i=<%=idioma%>"></a><br>
													</p>
												</div>
											</div>
											<div
												class="moto-widget moto-widget-text moto-preset-default                  moto-spacing-top-auto moto-spacing-right-auto moto-spacing-bottom-small moto-spacing-left-auto"
												data-widget="text" data-preset="default" data-spacing="aasa">
												<div
													class="moto-widget-text-content moto-widget-text-editable">
													<p class="moto-text_system_2">
														<a class="moto-link" data-action="pages" data-id="1"
															href="index.jsp?i=<%=idioma%>"></a><br>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div
								class="moto-widget moto-widget-spacer moto-preset-default               moto-spacing-top-small moto-spacing-right-auto moto-spacing-bottom-auto moto-spacing-left-auto"
								data-widget="spacer" data-preset="default" data-spacing="saaa">
								<div class="moto-widget-spacer-block" style="height: 1px;"></div>
							</div>
						</div>
						<div class="moto-cell col-sm-8" data-container="container">
							<div data-widget-id="wid__menu__586eaede4839b"
								class="moto-widget moto-widget-menu moto-preset-default moto-align-right moto-align-center_mobile-h moto-spacing-top-auto moto-spacing-right-auto moto-spacing-bottom-auto moto-spacing-left-auto"
								data-preset="default" data-widget="menu">
								<a href="#" class="moto-widget-menu-toggle-btn"><i
									class="moto-widget-menu-toggle-btn-icon fa fa-bars"></i></a>



								<!-- menu -->

								<ul
									class="moto-widget-menu-list moto-widget-menu-list_horizontal">


									<!-- menu inicio -->
									<li class="moto-widget-menu-item"><a
										href="index.jsp?i=<%=idioma%>" data-action="page"
										class="moto-widget-menu-link moto-widget-menu-link-level-1 moto-widget-menu-link-active moto-link"><%=idioma.equals("es") ? "*INICIO" : "*HOME"%></a>
									</li>


									<%
										String pathImagenes = request.getRealPath("/imagenes/");

										ConsultarInformacion consultarInformacion = new ConsultarInformacion();
										for (Menu loc : consultarInformacion.getMenusActivos()) {
											if (loc.getPoseeContenido().equals("S")) {
									%>
									<li class="moto-widget-menu-item"><a
										href="/web/menu.jsp?id=<%=loc.getId()%>&i=<%=idioma%>"
										data-action="page"
										class="moto-widget-menu-link moto-widget-menu-link-level-1 moto-link"><%=idioma.equals("es") ? loc.getTitulo() : loc.getTituloIngles()%></a>
									</li>

									<%
										} else {
									%>

									<li class="moto-widget-menu-item"><a href="#"
										data-action="page"
										class="moto-widget-menu-link moto-widget-menu-link-level-1 moto-widget-menu-link-submenu moto-link"><%=idioma.equals("es") ? loc.getTitulo() : loc.getTituloIngles()%><span
											class="fa moto-widget-menu-link-arrow"></span></a> <%
 	if (loc.gettSubmenus() != null && loc.gettSubmenus().size() > 0) {
 %>

										<ul class="moto-widget-menu-sublist">
											<%
												for (SubMenu det : loc.gettSubmenus()) {
											%>


											<li class="moto-widget-menu-item"><a
												href="/web/submenu.jsp?id=<%=det.getId()%>&i=<%=idioma%>"
												data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link"><%=idioma.equals("es") ? det.getTitulo() : det.getTituloIngles()%></a></li>



											<%
												}
											%>
										</ul> <%
 	}
 %></li>




									<%
										}
										}
									%>




									<li class="moto-widget-menu-item"><a href="#"
										data-action="page"
										class="moto-widget-menu-link moto-widget-menu-link-level-1 moto-link"><%=idioma.equals("es") ? "USUARIOS" : "USERS"%></a>


										<ul class="moto-widget-menu-sublist">



											<li class="moto-widget-menu-item"><a
												href="/web/planillaje.jsf?i=<%=idioma%>" data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link"><%=idioma.equals("es") ? "Planillaje" : "Planing"%></a></li>

											<li class="moto-widget-menu-item"><a
												href="/web/contactenos.jsf?i=<%=idioma%>" data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link"><%=idioma.equals("es") ? "Contáctenos" : "Contact Us"%></a></li>

											<li class="moto-widget-menu-item"><a
												href="/web/pqrs.jsf?i=<%=idioma%>" data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link">PQR</a></li>

											<li class="moto-widget-menu-item"><a
												href="/web/hojaVida.jsf?i=<%=idioma%>" data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link"><%=idioma.equals("es") ? "Enviar Hoja de Vida" : "Send Curriculum Vitae"%></a></li>

											<li class="moto-widget-menu-item"><a
												href="/web/login.jsf" data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link"><%=idioma.equals("es") ? "Acceso contenido página Web" : "Access to web page content"%></a></li>

											<li class="moto-widget-menu-item"><a
												href="https://mail.google.com/a/vijagual.com"
												data-action="page"
												class="moto-widget-menu-link moto-widget-menu-link-level-2 moto-link"><%=idioma.equals("es") ? "Acceso a correo" : "Access to mail"%></a></li>




										</ul></li>


									<li class="moto-widget-menu-item"><a
										href="/web/index.jsp?i=<%=idioma.equals("es") ? "en" : "es"%>"
										data-action="page"
										class="moto-widget-menu-link moto-widget-menu-link-level-1 moto-link"><%=idioma.equals("es") ? "ENGLISH VERSION" : "VERSIÓN ESPAÑOL"%></a>

									</li>






								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		</header>

		<section id="section-content" class="content page-1 moto-section"
			data-widget="section" data-container="section">
		
		
		<!--
		
		<div data-css-name="moto-container_content_564379ac"
			class="moto-widget moto-widget-container moto-container_content_564379ac"
			data-widget="container" data-container="container">
			<div data-widget-id="wid__slider__586eaede4b151"
				class="moto-widget moto-widget-slider moto-preset-default moto-spacing-top-auto moto-spacing-right-auto moto-spacing-bottom-auto moto-spacing-left-auto  moto-widget-slider-loader"
				data-widget="slider" data-preset="default">
				<ul class="moto-widget-slider-list"
					data-moto-slider-options='{&quot;itemsCount&quot;:3,&quot;slideshowEnabled&quot;:true,&quot;slideshowDelay&quot;:5,&quot;slideshowAnimationType&quot;:&quot;fade&quot;,&quot;showNextPrev&quot;:true,&quot;showPaginationDots&quot;:false,&quot;showSlideCaptions&quot;:true}'>
					------scriplet
						for (Baner loc : consultarInformacion.getFotosBaners()) {
					------scriplet

					--scriplet
					consultarInformacion.getSartaFoto(loc.gettFotoDecodificada())
					--scriplet


					--scriplet
					
						}
					
					--scriplet
				</ul>
			</div>
		</div>
		
		
		-->
		
		

<!-- baners --------------------------------------------------------->
		<%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(8).getContenido()
					: consultarInformacion.getSecciones().get(8).getContenidoIngles()%>


		<!-- eslogan 1 --------------------------------------------------------->
		<%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(0).getContenido()
					: consultarInformacion.getSecciones().get(0).getContenidoIngles()%>





		<div class="moto-widget moto-widget-row" data-widget="row">
			<div class="container-fluid">
				<div class="row">
					<div class="moto-cell col-sm-12" data-container="container">
						<div class="moto-widget moto-widget-spacer moto-preset-default"
							data-widget="spacer" data-preset="default" data-spacing="">
							<div class="moto-widget-spacer-block" style="height: 10px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>




		<!-- intro a calidad --------------------------------------------------------->
		<%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(1).getContenido()
					: consultarInformacion.getSecciones().get(1).getContenidoIngles()%>




		<div class="moto-widget moto-widget-row" data-widget="row">
			<div class="container-fluid">
				<div class="row">
					<div class="moto-cell col-sm-12" data-container="container">
						<div class="moto-widget moto-widget-spacer moto-preset-default"
							data-widget="spacer" data-preset="default" data-spacing="">
							<div class="moto-widget-spacer-block" style="height: 10px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!--buscador ---------------------------------------------------------- -->


		<div data-css-name="moto-container_content_56447efacc2f0"
			class="moto-widget moto-widget-container moto-container_content_56447efacc2f0"
			data-widget="container" data-container="container">
			<div class="moto-widget moto-widget-row row-fixed" data-widget="row">
				<div class="container-fluid">
					<div class="row">
						<div class="moto-cell col-sm-12" data-container="container">
							<div
								class="moto-widget moto-widget-text moto-preset-default                                   moto-spacing-top-large moto-spacing-right-auto moto-spacing-bottom-medium moto-spacing-left-auto"
								data-widget="text" data-preset="default" data-spacing="lama">
								<div class="moto-widget-text-content moto-widget-text-editable">
									<p class="moto-text_system_5"
										style="text-align: center; color: white; line-height: 24pt">
										<%=idioma.equals("es")
					? "Para facilitarle las cosas escriba una frase clave y lo redirigiremos a posibles opciones:"
					: "To make things easier, write a key phrase and redirect to options:"%>
										<br> <br> <input id="buscador" name="buscador" 
											type="text" onkeyup="cargarResultados('<%=idioma %>')"  
											placeholder='<%=idioma.equals("es") ? "Escriba aquí su frase clave..." : "Enter your key phrase here..."%>'
											style="width: 100%; height: 50px; color: red; font-size: 24px; text-align: center">
										<br /><br /> <span id="detalleBuscador" style="color:white"></span>

									</p>

									<br>
								</div>

							</div>
						</div>




					</div>
				</div>
			</div>
		</div>



		<!----------------------------------descripcion comercial  --> <%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(2).getContenido()
					: consultarInformacion.getSecciones().get(2).getContenidoIngles()%>

		<!-- herramientas online ---------------------------------------------------------- -->

		<%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(3).getContenido()
					: consultarInformacion.getSecciones().get(3).getContenidoIngles()%>

		<!-- galería --> <%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(4).getContenido()
					: consultarInformacion.getSecciones().get(4).getContenidoIngles()%>



		<hr style="width: 100%; height: 20px; color: #2F4576"
			class="moto-widget-divider-line">

		<!-- opiniones de  los clientes --> <%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(5).getContenido()
					: consultarInformacion.getSecciones().get(5).getContenidoIngles()%>





		</section>
	</div>

	<footer id="section-footer" class="footer moto-section"
		data-widget="section" data-container="section"> <!----------------------------------------------------- redes sociales -->

	<%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(6).getContenido()
					: consultarInformacion.getSecciones().get(6).getContenidoIngles()%>



	<!-------------------------------------------------- Política de datos -->
	<%=idioma.equals("es")
					? consultarInformacion.getSecciones().get(7).getContenido()
					: consultarInformacion.getSecciones().get(7).getContenidoIngles()%>

	<!-- mapa -->
	<div data-css-name="moto-container_footer_56444cb0"
		class="moto-widget moto-widget-container moto-container_footer_56444cb0"
		data-widget="container" data-container="container">
		<div
			class="moto-widget moto-widget-map moto-preset-default   moto-align-center moto-spacing-top-auto moto-spacing-right-auto moto-spacing-bottom-auto moto-spacing-left-auto"
			data-widget="map" data-preset="default" data-align="center"
			data-spacing="aaaa" data-width="100">
			<div class="moto-widget-cover"></div>


			<iframe
				src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d63336.914055838395!2d-73.1612209570282!3d7.1770803956688445!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8e68137e21abf921%3A0xae0445b1d1fb9a9a!2sFrigor%C3%ADfico+Vijagual!5e0!3m2!1ses!2ses!4v1483712828540"
				width="100" height="200" frameborder="0"
				data-address="Frigorífico Vijagual, Santander, Colombia"
				class="moto-widget-map-frame"
				style="border: 0px none; width: 100%; height: 330px;"
				allowfullscreen></iframe>

		</div>
	</div>
	</footer>

	<div data-moto-back-to-top-button class="moto-back-to-top-button"></div>
	<script
		src="mt-includes/js/website.assets.min32d5.js?_build=1483642826"
		type="text/javascript" data-cfasync="false"></script>
	<script type="text/javascript" data-cfasync="false">
		var websiteConfig = websiteConfig || {};
		websiteConfig.address = 'index.jsp';
		websiteConfig.apiUrl = 'api.json';
		websiteConfig.preferredLanguage = 'es';
		websiteConfig.back_to_top_button = {
			"enabled" : true,
			"topOffset" : 300,
			"animationTime" : 500
		};
		angular.module('website.plugins', []);
	</script>
	<script src="mt-includes/js/website.min1b36.js?_build=1483642815"
		type="text/javascript" data-cfasync="false"></script>

	<script type="text/javascript">
		function cargarResultados(idioma) {

			
			ajax = nuevoAjax();

	
			parametros = "texto="
					+ document.getElementById('buscador').value + "&idioma="
					+ idioma;

	

			
			url = "/web/resultados.jsp";

			ajax.onreadystatechange = function() {
				if (ajax.readyState == 1) {
					document.getElementById('detalleBuscador').innerHTML = "<img src='imagenes/ajax-loader3.gif'>";
				} else if (ajax.readyState == 4) {
					if (ajax.status == 200) {
						document.getElementById('detalleBuscador').innerHTML = "";
						document.getElementById('detalleBuscador').style.background = "";
						document.getElementById('detalleBuscador').innerHTML = ajax.responseText;

				

					} else if (ajax.status == 404) {
						document.getElementById('detalleBuscador').innerHTML = ajax.responseText;
						//document.getElementById('detalleBuscador').innerHTML = "-Inexistencia, contacte administrador-";
					} else {
						document.getElementById('detalleBuscador').innerHTML = ajax.responseText;
						//document.getElementById('detalleBuscador').innerHTML = "-Error, contacte administrador";
					}
				}  
			}

			ajax.open("POST", url, true);
			ajax.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded; charset=utf-8');
			ajax.send(parametros);
		}


		function nuevoAjax() {
			var xmlhttp = false;
			try {
				xmlhttp = new ActiveXObject("Msxml2.XMLHTTP"); // Creacion del objeto
				// AJAX para navegadores
				// no IE.
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); // Creacion del
					// objet AJAX
					// para IE.
				} catch (E) {
					xmlhttp = false;
				}
			}

			if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
				xmlhttp = new XMLHttpRequest();
			}
			return xmlhttp;
		}
	</script>



</body>


</html>