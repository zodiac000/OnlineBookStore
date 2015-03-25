/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.servlets;

import dbaccess.beans.CatalogItem;
import dbaccess.persistence.DBHelper;
import dbaccess.persistence.Book;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author ssome
 */
@WebServlet(name = "ViewList", urlPatterns = {"/ViewList"})
public class ViewList extends HttpServlet {
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
        
        // extract request parameters to a user data bean using
        // apache BeanUtils
        CatalogItem catalogItem = new CatalogItem();
        try {
            BeanUtils.populate(catalogItem,request.getParameterMap());
            String nextPage = null;

            // set up the bean as request parameter
            request.setAttribute("catalogItem",catalogItem);
            // check the operation and dispatch accordingly
            if (request.getParameter("bookManage") != null) {
                List<Book> results = getAll(em);
                
                
                if (results != null) {
                    // users have been found
                    request.setAttribute("results",results);
                    //dispatch to JSP for displaying results
                    nextPage = "/bookQuerry.jsp";
                } else {
                    
                    nextPage = "/bookQuerry.jsp";
                }
            } 
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request,response);
        } catch (IllegalAccessException | InvocationTargetException | ServletException | IOException ex) {
        } 
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

    
    private List<Book> getAll(EntityManager em){
        
        ArrayList<Book> result = new ArrayList<>();
        List l = DBHelper.findAllBook(em);
        if(l==null)
            return null;
        result.addAll(l);
        return result;
    }
    
    
    private List<Book> checkResults(List<Book> allresults,CatalogItem catalogItem) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b: allresults) {
            if (b.matches(catalogItem)) results.add(b);
        }
        if (results.isEmpty())  {
            return null;
        } else {
            return results;
        }
    }

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

    public void persist1(Object object) {
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
