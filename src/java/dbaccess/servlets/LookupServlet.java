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
import java.util.Map;

/**
 *
 * @author ssome
 */
@WebServlet(name = "LookupServlet", urlPatterns = {"/LookupServlet"})
public class LookupServlet extends HttpServlet {

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
            //m = request.getParameterMap();
            BeanUtils.populate(catalogItem, request.getParameterMap());

            String nextPage = null;

            // set up the bean as request parameter
            request.setAttribute("catalogItem", catalogItem);
            // check the operation and dispatch accordingly
            if (request.getParameter("kids") != null) {
                List<Book> results = getBooksByStore(em, "Kids");

                //   if (!"".equals(catalogItem.getItemID())) {
                // lookup by id
                //        results = getBookById(em, catalogItem);
                //     } else if (!"".equals(catalogItem.getName())) {
                // lookup by name
                //         results = getBooksByName(em, catalogItem);
                //      } else if (!"".equals(catalogItem.getCategory())) {
                // lookup by birthdate
                //          results = getBooksByCategory(em, catalogItem);
                //      }
                if (results != null) {
                    // users have been found
                    request.setAttribute("results", results);
                    //dispatch to JSP for displaying results
                    nextPage = "/kidsPage.jsp";
                } else {
                    // users not found
                    // dispatch to error page
                    nextPage = "/kidsPage.jsp";
                }
            } else if (request.getParameter("tech") != null) {
                List<Book> results = getBooksByStore(em, "Tech");

                if (results != null) {
                    // users have been found
                    request.setAttribute("results", results);
                    //dispatch to JSP for displaying results
                    nextPage = "/techPage.jsp";
                } else {
                    // users not found
                    // dispatch to error page
                    nextPage = "/techPage.jsp";
                }
            } else if (request.getParameter("add") != null) {
                if (catalogItem.isComplete()) {
                    if (DBHelper.addBook(em, utx, catalogItem)) {
                        List<Book> results = getAll(em);
                        // users have been found
                        request.setAttribute("results", results);
                        //dispatch to JSP for displaying results
                        nextPage = "/bookQuerry.jsp";

                        // nextPage = "/bookQuerry.jsp";///////////////////////////////////////
                    } else {
                        nextPage = "/failedAdd.jsp";//////////////////////////////
                    }
                } else {
                    nextPage = "/failedAddIncomplete.jsp";/////////////////////////////
                }
            } else if (request.getParameter("save") != null) {
                if (catalogItem.isComplete()) {
                    if (DBHelper.update(em, utx, catalogItem.getItemID(), catalogItem.getName(), catalogItem.getAuthor(), catalogItem.getDetail(), catalogItem.getCost(), catalogItem.getCategory())) {
                        List<Book> results = getAll(em);
                        // users have been found
                        request.setAttribute("results", results);
                        //dispatch to JSP for displaying results
                        nextPage = "/bookQuerry.jsp";

                        // nextPage = "/bookQuerry.jsp";///////////////////////////////////////
                    } else {
                        nextPage = "/failedAdd.jsp";//////////////////////////////
                    }
                } else {
                    nextPage = "/failedAddIncomplete.jsp";/////////////////////////////
                }
            } else if (request.getParameter("cancel") != null) {

                List<Book> results = getAll(em);
                // users have been found
                request.setAttribute("results", results);
                //dispatch to JSP for displaying results
                nextPage = "/bookQuerry.jsp";

                // nextPage = "/bookQuerry.jsp";///////////////////////////////////////
            } else {
                final String DELETE = "delete";
                final String EDIT = "edit";
                final String ID = "id";
                String bDel;
                String bEdit;
                String iID;
                List<Book> resultsall = getAll(em);
                for (int i = 0; i < resultsall.size(); i++) {
                    bDel = DELETE + i;
                    bEdit = EDIT + i;
                    if (request.getParameter(bDel) != null) {
                        iID = request.getParameter(ID + i).trim();
                        DBHelper.deleteBookById(em, utx, iID);
                        List<Book> results = getAll(em);
                        // users have been found
                        request.setAttribute("results", results);
                        nextPage = "/bookQuerry.jsp";
                        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                        dispatcher.forward(request, response);
                        return;
                    } else if (request.getParameter(bEdit) != null) {
                        iID = request.getParameter(ID + i).trim();
                        Book result = DBHelper.findBook(em, iID);
                        CatalogItem ci = new CatalogItem(result.getBOOK_ID(), result.getSHORT(), result.getAUTHOR(), result.getLONG(), String.valueOf(result.getCOST()), result.getCATEGORY());

                        // users have been found
                        request.setAttribute("row", ci);
                        nextPage = "/editPage.jsp";
                        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                        dispatcher.forward(request, response);
                        return;
                    }
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        } catch (IllegalAccessException | InvocationTargetException | ServletException | IOException ex) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response/'
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
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

    /**
     * Find a user by id and check if any that the other fields are valid
     */
    private List<Book> getBookById(EntityManager em, CatalogItem catalogItem) {
        Book book = DBHelper.findBook(em, catalogItem.getItemID());
        if (book == null) {
            return null;
        } else if (book.matches(catalogItem)) {
            ArrayList<Book> result = new ArrayList<>();
            result.add(book);
            return result;
        }
        return null;
    }

    private List<Book> getBooksByName(EntityManager em, CatalogItem catalogItem) {
        List<Book> allresults = DBHelper.findBookByName(em, catalogItem.getName());
        if (allresults == null) {
            return null;
        }
        return checkResults(allresults, catalogItem);
    }

    private List getBooksByCategory(EntityManager em, CatalogItem catalogItem) {
        List<Book> allresults = DBHelper.findBooksByCategory(em, catalogItem.getCategory());
        if (allresults == null) {
            return null;
        }
        return checkResults(allresults, catalogItem);
    }

    private List getBooksByStore(EntityManager em, String scat) {
        List<Book> allresults = DBHelper.findBooksByCategory(em, scat);
        if (allresults == null) {
            return null;
        }
        return allresults;
    }

    private List<Book> checkResults(List<Book> allresults, CatalogItem catalogItem) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : allresults) {
            if (book.matches(catalogItem)) {
                results.add(book);
            }
        }
        if (results.isEmpty()) {
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

    private List<Book> getAll(EntityManager em) {

        ArrayList<Book> result = new ArrayList<>();
        List l = DBHelper.findAllBook(em);
        if (l == null) {
            return null;
        }
        result.addAll(l);
        return result;
    }

    public void persist2(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public CatalogItem getCatalogItembyID(String id) {
        return DBHelper.findCatalogItem(em, id);
    }
}
