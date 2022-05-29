package com.ies.baroja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Coches;
import com.model.Duenos;
//import com.model.Jugadores;

/**
 * Servlet implementation class ServletForm
 * @author Apolonio García Ekua Bikie
 * @since mayo 2022
 */
@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String loginDueno = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Error: la llamadas GET no est�n permitidas").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	 throws ServletException, IOException {
		
	try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8"); 			
			
			if (request.getParameter("matriculay") != null) {
				insertarCocheAdmin(request, response);
				
			}else if (request.getParameter("nombrex") != null) {
				loginDueno(request, response);
				
			}else if (request.getParameter("dns") != null || request.getParameter("emaix") != null) {
				buscarDueno(request,response); //buscar o eliminar dueno
				
			}else if (request.getParameter("registronombre") != null){
				registroDueno(request, response);
				
			}else if (request.getParameter("matriculax") != null) {
				insertarCoche(request,response);
				
			}else {
				System.out.println("Opción no válida");
				
			}
				

		} catch (Exception ex) {
			System.out.println("Error en servlet");
			ex.printStackTrace();
			mostrarError(response, "¡¡Error en servlet!!");
		}
		
	} 

	
	//Método para logear a un dueño
	private static void loginDueno(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession sesion = request.getSession();
		String sNombre = request.getParameter("nombrex");
		String sDni = request.getParameter("dnix");
		String Apo = "apoloniog";
		//request.getParameter("dnix") == Apo &&
		
		
		// deberemos buscar el usuario en la base de datos, pero 
		Duenos dueno = ControllerD.getDueno(sDni);
		System.out.println("dopost.loginDueno(): Dueño a verificar desde html --> "+dueno);
		
		//ponemos un ejemplo en el mismo código
		if (sDni.equals(Apo) &&  sesion.getAttribute("dnix") == null) {
			//si los parámetros nombre y dni coinciden con el string Apo y además no hay sesión iniciada
			sesion.setAttribute("dnix", sDni);
			// redirijo a página con información 
			response.sendRedirect("bienvenidoAdmin.html"); 
			
		}else if (sNombre.equals("nombre") && sDni.equals("01234567A") 
					&& sesion.getAttribute("dnix") == null) {
			// si coincide nombre y dni y además no hay sesión iniciada
			sesion.setAttribute("dnix", sDni);
			// redirijo a página con información 
			response.sendRedirect("bienvenido.html"); 
			
		} else {
			// l�gica para login inv�lido
			mostrarError(response, " El usuario "+sNombre+ ", con DNI " + sDni + ", no tiene acceso :(");
		}

	}
	
	//Método para insertar/registrar dueño en la BBDD
	private static void registroDueno(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Cuarta contramedida, dentro del registroDueno, pero justo al principio");
		/** 1- recogida de datos */
		HttpSession sesion = request.getSession();
		String sDni = request.getParameter("dni");
		String sNombre = request.getParameter("registronombre");
		String Apo = "apoloniog";
		
		Duenos dueno = new Duenos(request.getParameter("registronombre"), request.getParameter("dni"),
				request.getParameter("apellidos"), request.getParameter("edad"), request.getParameter("pais"),
				request.getParameter("ciudad"), request.getParameter("numeroTelf"), 
				request.getParameter("email"));

		System.out.println("dopost.registroDueno(): Dueño a insertar desde html --> "+dueno);
		/** 2- Insertar jugador en la base de datos */
		boolean bRes = ControllerD.insertarDueno(dueno);

		/** 3- Redirigir a la pantalla bienvenido.jsp */
		if ((bRes) && sNombre.equals(Apo)){
			// si se inserta/registra el dueñoAmin y además no hay sesión iniciada
			sesion.setAttribute("dni", sDni);
			// redirijo a página con información de login exitoso
			response.sendRedirect("bienvenidoAdmin.html");
		
		}else if (bRes) {
			// si se inserta/registra el dueño y además no hay sesión iniciada
			sesion.setAttribute("dni", sDni);
			// redirijo a página con información de login exitoso
			response.sendRedirect("bienvenido.html");

		} else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error al registrarse");
		}
	}
	
	//Método para buscar dueño y borrar dueño
	private static void buscarDueno (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/**1- recogida de datos de la p�gina */
		HttpSession sesion = request.getSession();
		String sDni = request.getParameter("dns");
		String sEmail = request.getParameter("emaix");
		String Apo = "apoloniog";
		
		//si el parámetro es el email, procedemos a borrar al usuario
		if (sEmail.equals("email@gmail.com")) {
			//si el parámetro email coincide con el string
			//elimino la sesión
			//sesion.removeAttribute(sDni);
			
			/*
			 * 	//Duenos dueno = new Duenos(request.getParameter("nombre"), request.getParameter("dni"),
				//request.getParameter("apellidos"), request.getParameter("edad"), request.getParameter("pais"),
 				//request.getParameter("ciudad"), request.getParameter("numeroTelf"), 
				//request.getParameter("email"));

		//System.out.println("dopost.eliminarDueno(): Dueño a eliminar desde html --> "+dueno);
		//eliminar dueño de la base de datos
		boolean bRex = ControllerD.eliminarDueno(sEmail);
		
				// 3- Redirigir a la pantalla index.html
		if (bRex) {
			// si se ha borrado el dueño
			//elimino la sesión
			sesion.removeAttribute(sDni);
			// redirijo a página index
			response.sendRedirect("index.html");

		} else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error al eliminar el usuario");

		}
		*/
			
			// redirijo a página con información de login admin exitoso
			//response.sendRedirect("loginExito.jsp"); //usuario eliminado correctamente
			
			//si el parámetro es el dni, procedemos a mostrar los datos del dueño
		}else if (sDni != null){
			/**2- buscar dueno en la BBDD*/
			Duenos dueno = ControllerD.getDueno(sDni);
			
			/**3- devolver resultado a la p�gina */
			if(dueno != null ) {
				if (sDni.equals(Apo)) {
					sesion.setAttribute("dueño", dueno);
					response.sendRedirect("miperfilAdmin.jsp"); 
					
				} else {
					sesion.setAttribute("dueño", dueno);
					response.sendRedirect("miperfil.jsp");
					
				}
			} 
			else {
				mostrarError(response, sDni+" incorrecto");
			}
					
		}
		
	}
	
	
	//Método para insertar coches a la BBDD
	private static void insertarCoche(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/** 1- recogida de datos */
		/*HttpSession sesion = request.getSession();
		String sMarca = request.getParameter("marca");
		String sModelo = request.getParameter("modelo");
		String sDni = request.getParameter("dni");
		*/
		
		Coches coche = new Coches(request.getParameter("matriculax"), request.getParameter("marcax"),
				request.getParameter("modelox"), request.getParameter("velocidadx"), 
				request.getParameter("preciox"),request.getParameter("dna"), 
				request.getParameter("ciff"),request.getParameter("cifv"), 
				request.getParameter("numTarjetax"));

		System.out.println("dopost.insertarCoche(): Coche a insertar desde html --> "+coche);
		/** 2- Insertar coche en la base de datos */
		boolean bRes = ControllerC.insertarCoche(coche);

		/** 3- Redirigir a la pantalla  */
		if (bRes) {
			// si se inserta el coche
			// redirijo a página con información de operación exitosa
			response.sendRedirect("cocheExito.jsp"); 
			
		}else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error en la compra");

		}
	}
	
	
	//Método para insertar coches a la BBDD siendo admin
	private static void insertarCocheAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/** 1- recogida de datos */
		/*HttpSession sesion = request.getSession();
		String sMarca = request.getParameter("marca");
		String sModelo = request.getParameter("modelo");
		String sDni = request.getParameter("dni");
		*/
		
		Coches coche = new Coches(request.getParameter("matriculay"), request.getParameter("marcay"),
				request.getParameter("modeloy"), request.getParameter("velocidadMay"), 
				request.getParameter("precioy"),request.getParameter("dny"), 
				request.getParameter("cyff"),request.getParameter("cyfv"),
				request.getParameter("numTarjetay"));

		System.out.println("dopost.insertarCoche(): Coche a insertar desde html --> "+coche);
		/** 2- Insertar coche en la base de datos */
		boolean bRes = ControllerC.insertarCoche(coche);

		/** 3- Redirigir a la pantalla */
		if (bRes) {
			// si se inserta el coche
			// redirijo a página con información de operación exitosa
			response.sendRedirect("cocheExitoAdmin.jsp"); 
			
		}else {
			// Mostramos que se ha producido un error
			mostrarError(response, "Error en la inserción de vehículo");

		}
	}
	
	//Método para ...


	private static void mostrarError(HttpServletResponse response, String sMensaje) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>\n" + "<HEAD><TITLE>Página de Apolonio</TITLE>" + "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
				+ "</HEAD>\n" + "<BODY>\n" + "<div class=\"container mt-3\">\n"
				+ "<h1 class=\"text-danger\">Error <h1>\n"
				+ "<h2 class=\"text-danger\">"+sMensaje+"<h2>\n"
				+ "<img src=\"img/error.jpg\" class=\"rounded\" alt=\"error interno\">" + "</div></BODY></HTML>");
		out.close();
	}

}
