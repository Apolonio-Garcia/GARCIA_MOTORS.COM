package com.bbdd;

//import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Coches;
import com.model.Duenos;
//import com.model.Jugadores;

/**
 * Clase que centraliza los m�todos de acceso a BBDD
 * 
 * @author AGEB
 * @since Mayo 2022
 */

public class ConexionBBDD {

	Connection conexion; 
	int port = 3310;
	String host= "localhost";
	String db  = "cochespremium";
	String user="root";
	String password="";

	String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", host, port, db);

	public void conectar() throws SQLException {
		conexion =  DriverManager.getConnection(url, user, password);
	}	
	 

	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
	}

	public ResultSet ejecutarConsulta(String sentencia) {
		ResultSet rsResultado = null;
		try {
			System.out.println("Ejecutando: " + sentencia);
			PreparedStatement prepStatement = conexion.prepareStatement(sentencia);
			rsResultado = prepStatement.executeQuery();
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
		}
		return rsResultado;
	}
	
	
	public int insertar(Duenos dueno) {
		int iRes=0;
		String sInsert ="insert into duenos (DNI, Nombre, Apellidos, Edad, Pais, Ciudad, NumeroTelf, Email)  values (?, ?, ?, ?, ?, ?, ?, ?)";	
		  
		try {
			System.out.println("Ejecutando: " + sInsert);
			System.out.println("datos: " + dueno);
			PreparedStatement prepStatement = conexion.prepareStatement(sInsert);
			prepStatement.setString(1,dueno.getNombre());
			prepStatement.setString(2,dueno.getDni());	
			prepStatement.setString(3,dueno.getApellidos());
			prepStatement.setInt(4, Integer.parseInt(dueno.getEdad()) );
			prepStatement.setString(5,dueno.getPais());	
			prepStatement.setString(6,dueno.getCiudad());	
			prepStatement.setInt(7, Integer.parseInt(dueno.getNumeroTelf()) );
			prepStatement.setString(8,dueno.getEmail());			 			
			iRes =  prepStatement.executeUpdate();
			
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
		return iRes;
	}
	
	public int insertar(Coches coche) {
		int iRes=0;
		String sInsert ="insert into coches (Matricula, Marca, Modelo, VelocidadMax, Precio, DNI, CIFF, CIFV, NumTarjeta)  values (?, ?, ?, ?, ?, ?, ?, ?, ?)";	
		  
		try {
			System.out.println("Ejecutando: " + sInsert);
			System.out.println("datos: " + coche);
			PreparedStatement prepStatement = conexion.prepareStatement(sInsert);
			prepStatement.setString(1,coche.getMatricula());
			prepStatement.setString(2,coche.getMarca());	
			prepStatement.setString(3,coche.getModelo());
			prepStatement.setInt(4, Integer.parseInt(coche.getVelocidadMax()));
			prepStatement.setInt(5, Integer.parseInt(coche.getPrecio()));
			prepStatement.setString(6,coche.getDni());
			prepStatement.setString(7,coche.getCiff());
			prepStatement.setString(8,coche.getCifv());
			prepStatement.setInt(9, Integer.parseInt(coche.getNumTarjeta()));	 			
			iRes =  prepStatement.executeUpdate();
			
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
		return iRes;
	}

}
