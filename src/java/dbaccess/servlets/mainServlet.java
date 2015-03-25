/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dbaccess.beans.ShoppingCart;
import dbaccess.persistence.DBHelper;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.sound.midi.SysexMessage;
import dbaccess.beans.ItemOrder;

/**
 *
 * @author bin
 */
@WebServlet(name = "mainServlet", urlPatterns = {"/mainServlet"})
public class mainServlet extends HttpServlet {

    @PersistenceContext(unitName = "OnlineStore2PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            String nextPage = "/shoppingCart.jsp";
            HttpSession session = request.getSession();
            ShoppingCart cart;
            synchronized (session) {
                cart = (ShoppingCart) session.getAttribute("shoppingCart");
                // New visitors get a fresh shopping cart.
                // Previous visitors keep using their existing cart.
                if (cart == null) {
                    cart = new ShoppingCart();
                    session.setAttribute("shoppingCart", cart);
                }
                String itemID = request.getParameter("id");
                if (itemID != null) {

                    // If request specified an ID but no number,
                    // then customers came here via an "Add Item to Cart"
                    // button on a catalog page.
                    cart.addItem(DBHelper.findCatalogItem(em, itemID));
                    request.setAttribute("cart", cart.getItemsOrdered());
                    //dispatch to JSP for displaying results
                    nextPage = "/shoppingCart.jsp";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                    dispatcher.forward(request, response);
                } else {
                    String num = "num";
                    String id = "id";
                    String itemid;
                    String update = "update";
                    String button;
                    String readid = "";
                    for (int i = 0; i < cart.getItemsOrdered().size(); i++) {
                        itemid = id + i;
                        button = update + i;
                        if (request.getParameter(button) != null) {
                            readid = request.getParameter(itemid);

                            //if (((ItemOrder) cart.getItemsOrdered().get(i)).getItemID().trim().equals(readid.trim())) {
                            String numItemsString
                                    = request.getParameter(num + i);
                            int numItems;
                            try {
                                numItems = Integer.parseInt(numItemsString);
                            } catch (NumberFormatException nfe) {
                                numItems = 1;
                            }
                            cart.setNumOrdered(readid, numItems);
                            request.setAttribute("cart", cart.getItemsOrdered());
                            //dispatch to JSP for displaying results
                            nextPage = "/shoppingCart.jsp";
                            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                            dispatcher.forward(request, response);
                            return;
                        }
                    }

                }

            }
            // Whether or not the customer changed the order, show
            // order status.

        } catch (ServletException | IOException ex) {
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
