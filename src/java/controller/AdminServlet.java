/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entidade.Cliente;
import entidade.PedidosDoCliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import sessao.ClienteFacade;
import sessao.PedidosDoClienteFacade;
import sessao.GerenciadorPedido;

/**
 *
 * @author tgiunipero
 */
@WebServlet(name = "AdminServlet",
            urlPatterns = {"/admin/",
                           "/admin/visualizarpedidos",
                           "/admin/visualizarclientes",
                           "/admin/registroClientes",
                           "/admin/registroPedido",
                           "/admin/sair"})
@ServletSecurity( @HttpConstraint(transportGuarantee = TransportGuarantee.CONFIDENTIAL,rolesAllowed = {"lojaBikeMasterAdmin"}) )
public class AdminServlet extends HttpServlet {

    @EJB
    private GerenciadorPedido gerenciadorPedido;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private PedidosDoClienteFacade pedidosdoClienteFacade;

    private String userPath;
    private Cliente cliente;
    private PedidosDoCliente pedido;
    private List orderList = new ArrayList();
    private List clienteList = new ArrayList();


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession(true);
        userPath = request.getServletPath();

        // if viewCustomers is requested
        if (userPath.equals("/admin/visualizarclientes")) {
            clienteList = clienteFacade.findAll();
            request.setAttribute("clienteList", clienteList);
        }

        // if viewOrders is requested
        if (userPath.equals("/admin/visualizarpedidos")) {
            orderList = pedidosdoClienteFacade.findAll();
            request.setAttribute("orderList", orderList);
        }

        // if customerRecord is requested
        if (userPath.equals("/admin/registroClientes")) {

            // get customer id from request
            String customerId = request.getQueryString();

            // get customer details
            cliente = clienteFacade.find(Integer.parseInt(customerId));
            request.setAttribute("registroClientes", cliente);

            // get customer order details
            pedido = pedidosdoClienteFacade.findByCliente(cliente);
            request.setAttribute("pedido", pedido);
        }

        // if orderRecord is requested
        if (userPath.equals("/admin/registroPedido")) {

            // get customer id from request
            String pedidoId = request.getQueryString();

            // get order details
            Map orderMap = gerenciadorPedido.getDetalhesPedido(Integer.parseInt(pedidoId));

            // place order details in request scope
            request.setAttribute("cliente", orderMap.get("cliente"));
            request.setAttribute("produtos", orderMap.get("produtos"));
            request.setAttribute("registroPedido", orderMap.get("registroPedido"));
            request.setAttribute("produtosPedidos", orderMap.get("produtosPedidos"));
        }

        // if logout is requested
        if (userPath.equals("/admin/sair")) {
            sessao = request.getSession();
            sessao.invalidate();   // terminate session
            response.sendRedirect("/LojaBikeMaster/admin/");
            return;
        }

        // use RequestDispatcher to forward request internally
        userPath = "/admin/index.jsp";
        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}