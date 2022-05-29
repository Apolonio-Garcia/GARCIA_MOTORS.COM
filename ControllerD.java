package com.ies.baroja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.model.Duenos;
//import com.model.Jugadores;

import com.bbdd.ConexionBBDD;

public class ControllerD {
	private static String sConsultaDuenos = "SELECT Nombre, DNI, Apellidos, Edad, Pais, Ciudad, NumeroTelf, Email FROM duenos;";
	//private static String sConsultaDuenosX = "SELECT Nombre, DNI, Apellidos, Edad, Pais, Ciudad, NumeroTelf, Email FROM duenos;";
	public static LinkedList<Duenos> getDuenos() {
		// Objeto con la lista de dueños
		LinkedList<Duenos> listaDuenos = new LinkedList<Duenos>();
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaDuenos);
			if (rsResultado != null) {
				// Si hay resultado recuperamos los datos (como un FETCH de un CURSOR)
				while (rsResultado.next()) {
					// Creamos un objeto dueno por cada fila de la tabla (cada dueño)
					Duenos dueno = new Duenos(rsResultado.getString("Nombre"),rsResultado.getString("DNI"),
							rsResultado.getString("Apellidos"), String.valueOf(rsResultado.getInt("Edad")), rsResultado.getString("Pais"),
							rsResultado.getString("Ciudad"), String.valueOf(rsResultado.getInt("NumeroTelf")),
							rsResultado.getString("Email"));
					// Lo insertamos en la lista
					listaDuenos.add(dueno);
				}
			} else {
				System.out.println("La consulta no devuelve resultados");
			}
			System.out.println("Número de dueños=" + listaDuenos.size());

		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			miConexion.desconectar();
		}
		return listaDuenos;
	}

	public static Duenos getDueno(String sDniDueno) {

		String sConsultaBusq = "SELECT Nombre, DNI, Apellidos, Edad, Pais, Ciudad, NumeroTelf, Email FROM duenos"
				+ " where DNI = '" + sDniDueno + "';";
		Duenos dueno = null;
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			/** 1-conectar a la BBDD */
			miConexion.conectar();
			/** 2-lanzar la consulta */
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBusq);
			if (rsResultado != null) {
				/** 3-recuperar los datos */
				while (rsResultado.next()) {
					// Creamos un objeto dueno por cada fila de la tabla (cada dueño)
					dueno = new Duenos(rsResultado.getString("Nombre"), rsResultado.getString("DNI"),
							rsResultado.getString("Apellidos"), String.valueOf(rsResultado.getInt("Edad")), rsResultado.getString("Pais"),
							rsResultado.getString("Ciudad"), String.valueOf(rsResultado.getInt("NumeroTelf")), rsResultado.getString("Email"));
					//String.valueOf(rsResultado.getInt("Edad")),
					//String.valueOf(rsResultado.getInt("NumeroTelf")),
				}
			}//fin if
			else {
				System.out.println("Dueño no encontrado :|");
			}
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
		} finally {
			/** 4-cerrar conexión */
			miConexion.desconectar();
		}

		return dueno;
	}

	public static boolean insertarDueno(Duenos dueno) {
		boolean bRes = true;
		// Primero conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			System.out.println("Dueño a insertar: "+dueno);
			miConexion.conectar();
			int iRes = miConexion.insertar(dueno);
			System.out.println("Resultado de insertar el dueño = " + iRes);
		} catch (SQLException sqlex) {
			System.out.println("Error: " + sqlex.getMessage());
			sqlex.printStackTrace();
			bRes = false;
		} finally {
			miConexion.desconectar();
		}
		return bRes;
	}
	
	public static boolean eliminarDueno(String sEmailDueno) {
		boolean bRex = true;
		//consulta a ejecutar
		String sConsultaBuX = "DELETE FROM duenos"
				+ " where Email = '" + sEmailDueno + "';";
		// Conectamos a la BBDD
		ConexionBBDD miConexion = new ConexionBBDD();
		try {
			miConexion.conectar();
			// Lanzamos la consulta
			ResultSet rsResultado = miConexion.ejecutarConsulta(sConsultaBuX);
			
			if (rsResultado == null) {
				// Si no hay resultado, ejecución completada con éxito
				System.out.println("Usuario "+sEmailDueno+" eliminado correctamente.");

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
