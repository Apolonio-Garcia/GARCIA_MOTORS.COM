package com.ies.baroja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

//import com.model.Duenos;
import com.model.Coches;
//import com.model.Jugadores;

import com.bbdd.ConexionBBDD;

public class ControllerC {
	private static String sConsultaCoches = "SELECT Matricula, Marca, Modelo, VelocidadMax, Precio, DNI, CIFF, CIFV, NumTarjeta FROM coches;";
	//private static String sConsultaCochesX = "SELECT Matricula, Marca, Modelo, VelocidadMax, Precio, DNI, NumTarjeta FROM coches;";
	public static LinkedList<Coches> getCoches() {
		// Objeto con la lista de coches
		LinkedList<Coches> listaCoches = new LinkedList<Coches>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaCoches);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto coche por cada fila de la tabla (cada coche)
					Coches coche = new Coches(rsResultado.getString("Matricula"),rsResultado.getString("Marca"),
							rsResultado.getString("Modelo"), String.valueOf(rsResultado.getInt("VelocidadMax")), 
							String.valueOf(rsResultado.getInt("Precio")), rsResultado.getString("DNI"), 
							rsResultado.getString("CIFF"),rsResultado.getString("CIFV"),
							String.valueOf(rsResultado.getInt("NumTarjeta")));
							
					// Lo insertamos en la lista
					listaCoches.add(coche);
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de coches= " + listaCoches.size());

		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaCoches;
	}

	public static Coches getCoche(String sMatriculaCoche) {

		String sConsultaBusq = "SELECT Matricula, Marca, Modelo, VelocidadMax, Precio, DNI, CIFF, CIFV, NumTarjeta FROM coches"
				+ " where Matricula = '" + sMatriculaCoche + "';";
		Coches coche = null;
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			/** 1-conectar a la BBDD */
			miConexion.conectar();
			/** 2-lanzar la consulta */
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBusq);
			if (rsResultado != null) {
				/** 3-recuperar los datos */
				while (rsResultado.next()) {
					// Creamos un objeto coche por cada fila de la tabla (cada coche)
					coche = new Coches(rsResultado.getString("Matricula"),rsResultado.getString("Marca"),
							rsResultado.getString("Modelo"), String.valueOf(rsResultado.getInt("VelocidadMax")), 
							String.valueOf(rsResultado.getInt("Precio")), rsResultado.getString("DNI"), 
							rsResultado.getString("CIFF"),rsResultado.getString("CIFV"),
							String.valueOf(rsResultado.getInt("NumTarjeta")));
				}
			}//fin if
			else {
				System.out.println("Coche no encontrado :|");
			}
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			/** 4-cerrar conexión */
			miConexion.desconectar();
		}

		return coche;
	}

	public static boolean insertarCoche(Coches coche) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			System.out.println("Coche a insertar: "+coche);
			miConexion.conectar();
			int iRes = miConexion.insertar(coche);
			System.out.println("Resultado de insertar el coche = " + iRes);
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}
	
	public static boolean eliminarCoche(String sMatriculaCoche) {
		boolean bRex = true;
		//consulta a ejecutar
		String sConsultaBuX = "DELETE FROM coches"
				+ " where Matricula = '" + sMatriculaCoche + "';";
		// Conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBuX);
			
			if (rsResultado == null) {
				// Si no hay resultado, ejecución completada con éxito
				System.out.println("Vehículo "+sMatriculaCoche+" eliminado correctamente.");

			} else {
				System.out.println("La consulta no devuelve resultados");
			}

		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			bRex=false;
		} finally {
			miConexion.desconectar();
		}
		return bRex;



}
	
}
