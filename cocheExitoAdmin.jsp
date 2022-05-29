<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Coches"%>
<%@ page import="com.ies.baroja.ControllerC"%>
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
<title>Inserción exitosa</title>
</head>
<body>
	<div class="container mt-3">
		<h1>Datos insertados correctamente</h1>
		 <table class="table table-striped">
			 <thead class="table-dark">
			 	<tr>
				<th>Matrícula</th>
				<th>Marca</th>
				<th>Modelo</th>
				<th>Velocidad máxima</th>
				<th>Precio</th>
				<th>DNI</th>
				<th>Número de tarjeta bancaria</th>
				</tr>
			 </thead>
			 <tbody>
			<%
			LinkedList<Coches> lista = ControllerC.getCoches();
			for (int i = 0; i < lista.size(); i++) {
				if ( i % 2 == 0){
					out.println("<tr class='table-primary'>");
				}
				else {
					out.println("<tr class='table-success'>");
				}				
				out.println("<td>" + lista.get(i).getMatricula() + "</td>");
				out.println("<td>" + lista.get(i).getMarca() + "</td>");
				out.println("<td>" + lista.get(i).getModelo() + "</td>");
				out.println("<td>" + lista.get(i).getVelocidadMax() + "</td>");
				out.println("<td>" + lista.get(i).getPrecio() + "</td>");
				out.println("<td>" + lista.get(i).getDni() + "</td>");
				out.println("<td>" + lista.get(i).getNumTarjeta() + "</td>");
				out.println("</tr>");
			}
			%>
			</tbody>
		</table>

	</div>
	<br>
	<div class="container mt-3">
	    <button><a href="bienvenidoAdmin.html">Finalizar</a></button>

  
    <style type="text/css">
        a:link, a:visited, a:active {
            text-decoration:none;
        }
    </style>
	</div>
</body>
</html>