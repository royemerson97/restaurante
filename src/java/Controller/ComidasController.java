/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ComidaDAO;
import DAO.Tipo_ComidaDAO;
import Model.Comida;
import Model.Tipo_Comida;
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

/**
 *
 * @author Roy
 */
@WebServlet(name = "ComidasController", urlPatterns = {"/ComidasController"})
public class ComidasController extends HttpServlet {

    ComidaDAO comidaDAO;
    Tipo_ComidaDAO tipoComidaDAO;
    public void Init() {
        
    }
    
    public ComidasController(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        try {

            comidaDAO = new ComidaDAO(jdbcURL, jdbcUsername, jdbcPassword);
            tipoComidaDAO = new Tipo_ComidaDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
        doGet(request,response);
    }
    
     private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Comidas/index.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Comida Ocomida = new Comida(0, Integer.parseInt(request.getParameter("id_tipo_comida")), request.getParameter("comida"), Float.parseFloat(request.getParameter("precio")));
        boolean insertar = comidaDAO.insertar(Ocomida);
        mostrar(request, response);

    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Comidas/register.jsp");
        List<Tipo_Comida> tipos = tipoComidaDAO.listaTiposComidas();
        request.setAttribute("tipos", tipos);
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Comidas/index.jsp");
        List<Comida> listaComidas = comidaDAO.listaComidas();
        request.setAttribute("lista", listaComidas);
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Comida comida = comidaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("comida", comida);
        List<Tipo_Comida> tipos = tipoComidaDAO.listaTiposComidas();
        request.setAttribute("tipos", tipos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/Comidas/editar.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Comida comida = new Comida( Integer.parseInt(request.getParameter("id_comida")), Integer.parseInt(request.getParameter("id_tipo_comida")), request.getParameter("comida"), Float.parseFloat(request.getParameter("precio")));
        comidaDAO.actualizar(comida);
        mostrar(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Comida comida = comidaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        comidaDAO.eliminar(comida);
        mostrar(request, response);


    }

}
