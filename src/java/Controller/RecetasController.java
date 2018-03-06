/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ComidaDAO;
import DAO.RecetaDAO;
import DAO.UsuarioDAO;
import Model.Comida;
import Model.Receta;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RecetasController", urlPatterns = {"/RecetasController"})
public class RecetasController extends HttpServlet {
    
    RecetaDAO recetaDAO;
    UsuarioDAO usuarioDAO;
    public void Init() {
        
    }
    
    public RecetasController(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        try {

            recetaDAO = new RecetaDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Recetas/index.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Receta receta = new Receta( 0 , request.getParameter("titulo") , Integer.parseInt(request.getParameter("id_usuario")), request.getParameter("ingredientes") , request.getParameter("preparacion") , Integer.parseInt(request.getParameter("publicada")));
        boolean insertar = recetaDAO.insertar(receta);
        mostrar(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Recetas/register.jsp");
        List<Usuario> usuarios = usuarioDAO.listarArticulos();
        request.setAttribute("usuarios", usuarios);
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Recetas/index.jsp");
        List<Receta> listaArticulos = recetaDAO.listarArticulos();
        request.setAttribute("lista", listaArticulos);
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Receta receta = recetaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("re", receta);
        List<Usuario> usuarios = usuarioDAO.listarArticulos();
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Recetas/editar.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Receta receta = new Receta( Integer.parseInt(request.getParameter("id")), request.getParameter("titulo") , Integer.parseInt(request.getParameter("id_usuario")), request.getParameter("ingredientes") , request.getParameter("preparacion") , Integer.parseInt(request.getParameter("publicada")));
        recetaDAO.actualizar(receta);
        mostrar(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Receta receta = recetaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        recetaDAO.eliminar(receta);
        mostrar(request, response);

    }
}
