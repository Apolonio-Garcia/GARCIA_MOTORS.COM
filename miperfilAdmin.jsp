<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Duenos"%>
<%@ page import="com.ies.baroja.ControllerD"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<title>miperfil Admin</title>
</head>
<body>
	<div class="container mt-3">
		<h1>Datos del cliente</h1>
		 <table class="table table-striped">
			 <thead class="table-dark">
			 	<tr>
				<th>Nombre</th>
				<th>DNI</th>
				<th>Apellidos</th>
				<th>Edad</th>
				<th>País</th>
				<th>Ciudad</th>
				<th>Número de teléfono</th>
				<th>Email</th>
				</tr>
			 </thead>
			 <tbody>
			<%
			LinkedList<Duenos> lista = ControllerD.getDuenos();
			for (int i = 0; i < lista.size(); i++) {
				if ( i % 2 == 0){
					out.println("<tr class='table-primary'>");
				}
				else {
					out.println("<tr class='table-success'>");
				}				
				out.println("<td>" + lista.get(i).getNombre() + "</td>");
				out.println("<td>" + lista.get(i).getDni() + "</td>");
				out.println("<td>" + lista.get(i).getApellidos() + "</td>");
				out.println("<td>" + lista.get(i).getEdad() + "</td>");
				out.println("<td>" + lista.get(i).getPais() + "</td>");
				out.println("<td>" + lista.get(i).getCiudad() + "</td>");
				out.println("<td>" + lista.get(i).getNumeroTelf() + "</td>");
				out.println("<td>" + lista.get(i).getEmail() + "</td>");
				out.println("</tr>");
			}
			%>
			</tbody>
		</table>

	</div>
	<br>
	<div class="container mt-3">
	    <button><a href="miperfilAdmin.html">Volver</a></button>

  
    <style type="text/css">
        a:link, a:visited, a:active {
            text-decoration:none;
        }
    </style>
	</div>
</body>
</html>