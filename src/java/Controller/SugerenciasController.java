/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ComidaDAO;
import DAO.SugerenciaDAO;
import DAO.UsuarioDAO;
import Model.Comida;
import Model.Sugerencia;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SugerenciasController", urlPatterns = {"/SugerenciasController"})
public class SugerenciasController extends HttpServlet {
    
    SugerenciaDAO sugerenciaDAO;
    UsuarioDAO usuarioDAO;
    public void Init() {
        
    }
    
    public SugerenciasController(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        try {

            sugerenciaDAO = new SugerenciaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            usuarioDAO = new UsuarioDAO(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Hola Servlet..");
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            switch (action) {
                case "index":
                    index(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "register":
                    System.out.println("entro");
                    registrar(request, response);
                    break;
                case "mostrar":
                    mostrar(request, response);
                    break;
                case "showedit":
                    showEditar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    
    }
    
         private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //mostrar(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Sugerencias/index.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Sugerencia sugerencia = new Sugerencia(0 , Integer.parseInt(request.getParameter("id_usuario")) , request.getParameter("sugerencia") , Integer.parseInt(request.getParameter("publicada")));
        boolean insertar = sugerenciaDAO.insertar(sugerencia);
        mostrar(request, response);

    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Sugerencias/register.jsp");
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Sugerencias/index.jsp");
        List<Sugerencia> listaSugerencias = sugerenciaDAO.listaSugerencias();
        request.setAttribute("lista", listaSugerencias);
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Sugerencia sugerencia = sugerenciaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("su", sugerencia);
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Sugerencias/editar.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Sugerencia sugerencia = new Sugerencia( Integer.parseInt(request.getParameter("id")) , Integer.parseInt(request.getParameter("id_usuario")) , request.getParameter("sugerencia") , Integer.parseInt(request.getParameter("publicada")));
        sugerenciaDAO.actualizar(sugerencia);
        mostrar(request, response);

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Sugerencia sugerencia = sugerenciaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        sugerenciaDAO.eliminar(sugerencia);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vistas/Sugerencias/index.jsp");
        dispatcher.forward(request, response);

    }
}
